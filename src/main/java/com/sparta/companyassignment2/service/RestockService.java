package com.sparta.companyassignment2.service;

import com.sparta.companyassignment2.entity.ProductNotificationHistory;
import com.sparta.companyassignment2.entity.ProductUserNotificationHistory;
import com.sparta.companyassignment2.entity.User;
import com.sparta.companyassignment2.enums.TransferStatus;
import com.sparta.companyassignment2.repository.ProductNotificationHistoryRepository;
import com.sparta.companyassignment2.repository.ProductUserNotificationHistoryRepository;
import com.sparta.companyassignment2.repository.ProductUserNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestockService {

    static final int RESTOCK_CNT = 10;

    private final ProductNotificationHistoryRepository notificationHistoryRepository;
    private final ProductUserNotificationRepository userNotificationRepository;
    private final ProductUserNotificationHistoryRepository userNotificationHistoryRepository;

    @Transactional
    public void transfer(Long productId) {
        //재입고 회차 증가(고정 개수)
        int round = notificationHistoryRepository.findTopByProductIdOrderByRestockRoundDesc(productId);
        ProductNotificationHistory restock
                = new ProductNotificationHistory(productId, round + 1, RESTOCK_CNT);
        notificationHistoryRepository.save(restock);

        //해당 상품 재입고알림 신청 한 유저 가져오기
        List<User> userList = userNotificationRepository.findUserByProductIdOrderByCreatedAt(productId);
        List<ProductUserNotificationHistory> punh
                = userList.stream()
                .map(user ->
                        new ProductUserNotificationHistory(productId
                                , round + 1
                                , TransferStatus.IN_PROGRESS, user))
                .toList();

        //유저알림 히스토리 저장
        List<ProductUserNotificationHistory> savePunh
                = userNotificationHistoryRepository.saveAll(punh);


    }
}
