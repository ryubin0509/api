package com.sakila.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sakila.api.dto.StoreDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.StoreEntity;

public interface StoreRepository extends JpaRepository<StoreEntity, Integer> {

	// 자식키 있는지 확인 
	Long countByAddressEntity(AddressEntity addressEntity);

}
