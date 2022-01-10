package com.nurcan.stocksservice.service;

import com.nurcan.stocksservice.domain.model.Stock;

import java.util.List;

public interface StockService {
    List<Stock> getAll();

    Stock saveStock(Stock stock);

    Stock getById(Long id);

    Stock update(Long id, Stock stock);

    void delete(Long id);
}
