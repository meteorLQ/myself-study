package com.lq.distributedlock.controller;

import com.lq.distributedlock.service.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StockController {
    @Resource
    private StockService stockService;

    @GetMapping("stock/deduct")
    public String deduct(){
        stockService.deduct();
        return "hello stock deduct!!!";
    }

}
