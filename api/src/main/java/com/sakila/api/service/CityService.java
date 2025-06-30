package com.sakila.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.CityDto;
import com.sakila.api.entity.CityEntity;
import com.sakila.api.entity.CountryEntity;
import com.sakila.api.repository.AddressRepository;
import com.sakila.api.repository.CityRepository;
import com.sakila.api.repository.CountryReoisitory;

@Transactional
@Service
public class CityService {
	private CityRepository cityRepository;
	private CountryReoisitory countryRepository;
	private AddressRepository addressRepository;
	
	public CityService(CityRepository cityRepository, CountryReoisitory countryRepository , 
			 AddressRepository addressRepository) {
		this.cityRepository = cityRepository;
		this.countryRepository = countryRepository;
		this.addressRepository = addressRepository;
	}
	
	// city 삭제
	public boolean delete(int cityId) {
		if(0 ==addressRepository.countByCityEntity(cityRepository.findById(cityId).orElse(null))) {
			cityRepository.deleteById(cityId);
			return true;
			
		} else {
			System.out.println("자식테이블 외래키 참조행 존재합니다.");
			return false;
		}
		
	}
	
	
	
	// 수정
	public void update(CityDto cityDto) {
		 
		CityEntity city = cityRepository.findById(cityDto.getCityId()).orElse(null);
		city.setCity(cityDto.getCity());
		 
	}
	
	
	// 입력
	public void save(CityDto cityDto) {
		CityEntity city = new CityEntity();
		city.setCity(cityDto.getCity());

		CountryEntity country = countryRepository.findById(cityDto.getCountryId())
		    .orElseThrow(() -> new RuntimeException("Invalid countryId"));

		city.setCountryEntity(country);
	    
		cityRepository.save(city);
	}
	
	public CityEntity findById(int cityId){
		return cityRepository.findById(cityId).orElse(null);
	}
	
	
	// 조회
	public List<CityEntity> findAll(){
		return cityRepository.findAll();
	}
}
