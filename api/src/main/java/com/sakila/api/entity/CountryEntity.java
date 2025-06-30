package com.sakila.api.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "country")
@Data
public class CountryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_id")
	private int countryId;
	
	@Column(name = "country")
	private String country;
	
    @Column(name = "last_update", 
            nullable = false,
            insertable = false, 
            updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp lastUpdate;
	
	
	
	/*
	@OneToMany
	private CityEntity cityEntity; 부모에서 외래키 잡는방법
	*/ 
}