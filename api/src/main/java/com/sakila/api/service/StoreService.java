package com.sakila.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.StoreDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.StoreEntity;
import com.sakila.api.repository.AddressRepository;
import com.sakila.api.repository.StoreRepository;

@Service
@Transactional
public class StoreService {
	private StoreRepository storeRepository;
	private AddressRepository addressRepository;
	
	public StoreService(StoreRepository storeRepository, AddressRepository addressRepository) {
		this.storeRepository = storeRepository;
		this.addressRepository = addressRepository;
	
	}
	
	
	// 삭제
	public boolean delete(int storeId) {
		if(storeRepository.existsById(storeId)) {
			storeRepository.deleteById(storeId);
			return true;
		}
		return false;
	}
	
	
	
	public void update(StoreDto storeDto) {
		
		StoreEntity updateStoreEntity = storeRepository.findById(storeDto.getStoreId()).orElse(null); // 현재 dto StoreID 불러오기
		AddressEntity address = addressRepository.findById(storeDto.getAddressId()).orElse(null);
		
		// 필드 수정
		updateStoreEntity.setManagerStaffId(storeDto.getManagerStaffId());
		updateStoreEntity.setAddressEntity(address);
		
		
	}
	
	
	// 입력
	public void save(StoreDto storeDto) {
		StoreEntity storeEntity = new StoreEntity();
		storeEntity.setManagerStaffId(storeDto.getManagerStaffId());
		
		AddressEntity address = addressRepository.findById(storeDto.getAddressId()).orElse(null); 
		// 외래키 이전에 정보를 address로 가서 구한뒤 그값을 Entity 값에 삽입
		
		storeEntity.setAddressEntity(address);
		
		storeRepository.save(storeEntity);  // 입력
		
	}
	
	
	// 전체 조회
	public List<StoreEntity>findAll(){
		return storeRepository.findAll();
	}


	public StoreEntity findById(int storeId) {
		
		return storeRepository.findById(storeId).orElse(null);
	}
}
