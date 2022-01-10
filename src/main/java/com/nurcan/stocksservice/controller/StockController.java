package com.nurcan.stocksservice.controller;

import com.nurcan.stocksservice.domain.model.Stock;
import com.nurcan.stocksservice.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    private StockService stockService;

    public StockController(StockService stockService) {
        super();
        this.stockService = stockService;
    }

    @Secured({ROLE_ADMIN})
    @GetMapping
    public ResponseEntity<List<Stock>> getAll() {
        return new ResponseEntity<List<Stock>>(stockService.getAll(), HttpStatus.OK);
    }

    // build create stock REST API
    @PostMapping
    public ResponseEntity<Stock> create(@RequestBody Stock stock) {
        return new ResponseEntity<Stock>(stockService.saveStock(stock), HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getById(@PathVariable Long id) {
        return new ResponseEntity<Stock>(stockService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> update(@PathVariable Long id, @RequestBody Stock stock) {

        return new ResponseEntity<Stock>(stockService.update(id, stock), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateStock(@PathVariable Long id) {
        stockService.delete(id);
    }
}