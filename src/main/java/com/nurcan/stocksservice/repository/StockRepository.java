package com.nurcan.stocksservice.repository;

import com.nurcan.stocksservice.domain.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
