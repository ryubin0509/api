package com.sakila.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.CustomerEntity;

public interface CustomerRepository  extends JpaRepository<CustomerEntity, Integer>{
	// 자식키 있는지 확인 
	Long countByAddressEntity(AddressEntity addressEntity);
}
