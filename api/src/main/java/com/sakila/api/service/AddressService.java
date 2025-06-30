package com.sakila.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.AddressDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.CityEntity;
import com.sakila.api.repository.AddressRepository;
import com.sakila.api.repository.CityRepository;
import com.sakila.api.repository.CustomerRepository;
import com.sakila.api.repository.StoreRepository;

@Service
@Transactional
public class AddressService {
	private AddressRepository addressRepository;
	private CityRepository cityRepository;
	private StoreRepository storeRepository;
	private CustomerRepository customerRepository;
	
	public AddressService(AddressRepository addressRepository, CityRepository cityRepository) {
		this.addressRepository = addressRepository;
		this.cityRepository = cityRepository;
		this.storeRepository = storeRepository;
		this.customerRepository = customerRepository;
	}
	
	
	//  삭제
	public boolean delete(int addressId) {
		if(0== storeRepository.countByAddressEntity(addressRepository.findById(addressId).orElse(null)) && 0 == customerRepository.countByAddressEntity(addressRepository.findById(addressId).orElse(null))) {
			return true;
		} else {
			System.out.println("자식테이블 외래키 참조행 존재합니다.");
			return false;
		}
					
	}
	
	
	
	// 업데이트
	public void update(AddressDto addressDto) {
		AddressEntity addressEntity = new AddressEntity();
		addressEntity = addressRepository.findById(addressDto.getAddressId()).orElse(null);
		
		addressEntity.setAddress(addressDto.getAddress());
		addressEntity.setDistrict(addressDto.getDistrict());
		addressEntity.setPostalCode(addressDto.getPostalCode());
		addressEntity.setPhone(addressDto.getPhone());
		
	}
	
	
	
	// 저장
	public void save(AddressDto addressDto) {
		AddressEntity addressEntity = new AddressEntity();
		
		addressEntity.setAddress(addressDto.getAddress());
		addressEntity.setAddress2(addressDto.getAddress2());
		addressEntity.setDistrict(addressDto.getDistrict());
		addressEntity.setPostalCode(addressDto.getPostalCode());
		addressEntity.setPhone(addressDto.getPhone());
		
		CityEntity cityEntity = cityRepository.findById(addressDto.getCityId()).orElse(null); 
		
		addressEntity.setCityEntity(cityEntity);
		
		addressRepository.save(addressEntity);
		
		
	}
	
	public  AddressEntity findById(int addressId){
		return addressRepository.findById(addressId).orElse(null);
	}
	
	// 전체 조회 
	public List<AddressEntity>findAll(){
		return addressRepository.findAll();
	}
	
}
