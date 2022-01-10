package com.nurcan.stocksservice.service.impl;

import com.nurcan.stocksservice.domain.exception.StockLockedException;
import com.nurcan.stocksservice.domain.model.Stock;
import com.nurcan.stocksservice.repository.StockRepository;
import com.nurcan.stocksservice.service.StockService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private StockRepository stockRepository;


    public StockServiceImpl(StockRepository stockRepository) {
        super();
        this.stockRepository = stockRepository;
    }

    @Override
    public List<Stock> getAll() {
        return stockRepository.findAll();
    }

    @Override
    public Stock saveStock(Stock stock) {
        stock.setLastUpdate(LocalDateTime.now());
        return stockRepository.save(stock);
    }

    @Override
    public Stock getById(Long id) {
        return stockRepository.getById(id);
    }

    @Override
    public Stock update(Long id, Stock stock) {
        Stock currentStock = getById(id);
        if (currentStock.getLastUpdate().isBefore(LocalDateTime.now().minusMinutes(5))) {
            throw new StockLockedException();
        }

        currentStock.setCurrentPrice(stock.getCurrentPrice());
        return stockRepository.save(stock);
    }

    @Override
    public void delete(Long id) {
        Stock currentStock = getById(id);
        if (currentStock.getLastUpdate().isBefore(LocalDateTime.now().minusMinutes(5))) {
            throw new StockLockedException();
        }
        stockRepository.deleteById(id);
    }
}
