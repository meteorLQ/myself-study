package com.lq.distributedlock.service;

import com.lq.distributedlock.entity.Stock;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    private Stock stock=new Stock();

    public synchronized void deduct(){
        stock.setStock(stock.getStock()-1);
        System.out.println("扣减成功，库存余量："+stock.getStock());
    }
}
