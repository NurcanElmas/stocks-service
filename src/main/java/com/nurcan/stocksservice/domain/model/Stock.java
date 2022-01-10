package com.nurcan.stocksservice.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double currentPrice;

    @Column(nullable = false)
    private LocalDateTime lastUpdate;

    @Column(nullable = false)
    private String createdBy;
}
