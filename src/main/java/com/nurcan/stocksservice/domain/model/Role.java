package com.nurcan.stocksservice.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private RoleType name;

    private String description;

    private LocalDateTime createdOn;

    private LocalDateTime lastUpdate;

}
