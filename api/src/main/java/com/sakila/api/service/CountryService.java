package com.sakila.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.CountryDto;
import com.sakila.api.entity.CountryEntity;
import com.sakila.api.repository.CityRepository;
import com.sakila.api.repository.CountryReoisitory;

@Service
@Transactional
public class CountryService {
	private CountryReoisitory countryRepository;
	private CityRepository cityRepository;
	
	
	// 한행 조회
	public CountryEntity findById(int countryId) {
		return countryRepository.findById(countryId).orElse(null);
	}
	
	// 전체 조회
	public List<CountryEntity> findAll() {
		return countryRepository.findAll();
	}
	
	public CountryService (CountryReoisitory countryRepository, CityRepository cityRepository) { // 생성자 주입
		this.countryRepository = countryRepository;
		this.cityRepository = cityRepository;
	}
	

	//  country 삭제
	public boolean delete(int countryId) { 
		if (0 == cityRepository.countByCountryEntity(countryRepository.findById(countryId).orElse(null))){
			countryRepository.deleteById(countryId);
			return true;
		} else {
			System.out.println("자식테이블에 외래키로 참조하는 행이 존재합니다.");
			return false;
		}
		
	}
	
	
	// country 업데이트
	public void update(CountryDto countryDto) {
	   
		// ① DB에서 기존 엔티티 조회
	    CountryEntity updateCountryEntity = countryRepository
	        .findById(countryDto.getCountryId())
	        .orElseThrow(() -> new RuntimeException("존재하지 않는 countryId"));

	    // ② 필드 수정
	    updateCountryEntity.setCountry(countryDto.getCountry());
	}


	
	// CountryEntity 입력
	public void save(CountryDto countryDto) {
		CountryEntity saveCountryEntity = new CountryEntity();
		saveCountryEntity.setCountry(countryDto.getCountry());
		countryRepository.save(saveCountryEntity);
	}
	

}
