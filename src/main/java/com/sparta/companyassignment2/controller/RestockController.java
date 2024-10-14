package com.sparta.companyassignment2.controller;

import com.sparta.companyassignment2.service.RestockService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class RestockController {

    private final RestockService restockService;

    public RestockController(RestockService restockService) {
        this.restockService = restockService;
    }

    @PostMapping("/{productId}/notifications/re-stock")
    public String transferNotification(@PathVariable Long productId){
        restockService.transfer(productId);
        return "";
    }
}
