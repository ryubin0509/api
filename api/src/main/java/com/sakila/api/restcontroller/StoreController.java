package com.sakila.api.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.api.dto.StoreDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.StoreEntity;
import com.sakila.api.service.StoreService;

@RestController
public class StoreController {
	private StoreService storeService;
	
	public StoreController(StoreService storeService) {
		this.storeService = storeService;
	}
	
	
	// 삭제 
	@DeleteMapping("/store/{storeId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int storeId) {
		
		boolean result = storeService.delete(storeId);
		if(result) {
			return new ResponseEntity<String>("삭제 성공", HttpStatus.OK);
		}
		return new ResponseEntity<String>("삭제 실패", HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
	
	
	
	// 수정
	@PatchMapping("/store")
	public ResponseEntity<String>update(@RequestBody StoreDto storeDto){
		System.out.println(storeDto.toString());
		storeService.update(storeDto);
		
		return new ResponseEntity<String>("store 수정성공", HttpStatus.OK);
		
	}
	
	// 입력
	@PostMapping("/store")
	public ResponseEntity<String>insert(@RequestBody StoreDto storeDto){
		System.out.println(storeDto.toString());
		storeService.save(storeDto);
		
		return new ResponseEntity<String>("store 입력성공" , HttpStatus.OK); 
	}
	
	@GetMapping("/store/{storeId}")
	public ResponseEntity<StoreEntity> addressOne(@PathVariable int storeId){
		return new ResponseEntity<StoreEntity>(storeService.findById(storeId),HttpStatus.OK);
	}
	
	// 전체 조회
	@GetMapping("/store") 
	public ResponseEntity<List<StoreEntity>> store(){
		return new ResponseEntity<List<StoreEntity>>(storeService.findAll(), HttpStatus.OK);
	}
}
