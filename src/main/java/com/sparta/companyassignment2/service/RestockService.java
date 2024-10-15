package com.sparta.companyassignment2.service;

import com.sparta.companyassignment2.entity.Product;
import com.sparta.companyassignment2.entity.ProductNotificationHistory;
import com.sparta.companyassignment2.entity.ProductUserNotification;
import com.sparta.companyassignment2.entity.ProductUserNotificationHistory;
import com.sparta.companyassignment2.enums.TransferStatus;
import com.sparta.companyassignment2.repository.ProductNotificationHistoryRepository;
import com.sparta.companyassignment2.repository.ProductRepository;
import com.sparta.companyassignment2.repository.ProductUserNotificationHistoryRepository;
import com.sparta.companyassignment2.repository.ProductUserNotificationRepository;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestockService {

    static final int RESTOCK_CNT = 10;

    @Autowired
    private final EntityManager entityManager;

    private final ProductRepository productRepository;
    private final ProductNotificationHistoryRepository notificationHistoryRepository;
    private final ProductUserNotificationRepository userNotificationRepository;
    private final ProductUserNotificationHistoryRepository userNotificationHistoryRepository;

    @Transactional
    public void transfer(Long productId) {

        //상품 조회
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("상품이 존재 하지 않습니다."));

        //재입고 회차 증가(고정 개수)
        product.update(product.getRestockRound() + 1, RESTOCK_CNT);

        ProductNotificationHistory newRestock
                = new ProductNotificationHistory(product, product.getRestockRound(), TransferStatus.IN_PROGRESS);

        //해당 상품 재입고알림 신청 한 유저 가져오기
//        List<Long> userList = userNotificationRepository.findUserByProductIdOrderByCreatedAt(product);


        Refill refill = Refill.intervally(500, Duration.ofSeconds(1));
        Bandwidth limit = Bandwidth.classic(product.getRestockNotificationUser().size()
                , refill);

        //RateLimiter 초기 설정
        Bucket bucket = Bucket.builder()
                .addLimit(limit).build();
        bucket.addTokens(500);

        //유저에게 알림 히스토리 저장 리스트
        List<ProductUserNotificationHistory> transferNoticeList = new ArrayList<>();

        for(ProductUserNotification user : product.getRestockNotificationUser()){
            if(bucket.tryConsume(1)){//토큰이 남아있다면 리스트에 담음
                transferNoticeList.add(new ProductUserNotificationHistory(product.getId()
                        , user.getUserId()
                        , product.getRestockRound()
                        , LocalDateTime.now()));
            }else{
                if(!transferNoticeList.isEmpty()){
                    userNotificationHistoryRepository.saveAll(transferNoticeList);
                }

                entityManager.refresh(product);
                if(product.getStock() == 0){//재고 없음
                    newRestock.stopTransfer(user.getUserId(), TransferStatus.CANCELED_BY_SOLD_OUT);
                    return;
                }else{
                    transferNoticeList.clear();
                    bucket.addTokens(500);
                }
            }
        }

        //비어있지 않다면 한번 더 저장해줌
        if(!transferNoticeList.isEmpty()){
            userNotificationHistoryRepository.saveAll(transferNoticeList);
        }

    }
}
