package com.sakila.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.CustomerDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.CustomerEntity;
import com.sakila.api.entity.StoreEntity;
import com.sakila.api.repository.AddressRepository;
import com.sakila.api.repository.CustomerRepository;
import com.sakila.api.repository.StoreRepository;

@Service
@Transactional
public class CustomerService {
	private CustomerRepository customerRepository;
	private AddressRepository addressRepository;
	private StoreRepository storeRepository;
	
	
	
	public CustomerService(CustomerRepository customerRepository,
			AddressRepository addressRepository, StoreRepository storeRepository) {
		this.customerRepository = customerRepository;
		this.addressRepository = addressRepository;
		this.storeRepository = storeRepository;
	}
	
	
	// 수정
	public void update(CustomerDto customerDto) {
		
		StoreEntity storeEntity = new StoreEntity();
		storeEntity = storeRepository.findById(customerDto.getStoreId()).orElse(null);
		
		AddressEntity addressEntity = new AddressEntity();
		addressEntity = addressRepository.findById(customerDto.getAddressId()).orElse(null);
		
		CustomerEntity customerEntity = customerRepository.findById(customerDto.getCustomerId()).orElse(null);
		
		customerEntity.setFirstName(customerDto.getFirstName());
		customerEntity.setLastName(customerDto.getLastName());
		customerEntity.setEmail(customerDto.getEmail());
		customerEntity.setActive(customerDto.getActive());
		customerEntity.setStoreEntity(storeEntity);
		customerEntity.setAddressEntity(addressEntity);
		
	}
	
	
	
	
	// 입력
	public void save(CustomerDto customerDto) {
		
		StoreEntity storeEntity = new StoreEntity();
		storeEntity = storeRepository.findById(customerDto.getStoreId()).orElse(null);
		
		AddressEntity addressEntity = new AddressEntity();
		addressEntity = addressRepository.findById(customerDto.getAddressId()).orElse(null);
		
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setFirstName(customerDto.getFirstName());
		customerEntity.setLastName(customerDto.getLastName());
		customerEntity.setEmail(customerDto.getEmail());
		customerEntity.setActive(customerDto.getActive());
		customerEntity.setStoreEntity(storeEntity);
		customerEntity.setAddressEntity(addressEntity);
		
		customerRepository.save(customerEntity);
		
	}
	
	// 조회
	public List<CustomerEntity> findAll(){
		return customerRepository.findAll();
				
	}

	// 한행 조회
	public CustomerEntity findById(int customerId) {
		return customerRepository.findById(customerId).orElse(null);
	}
}
