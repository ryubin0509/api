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
import org.springframework.web.bind.annotation.RestController;

import com.sakila.api.dto.CustomerDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.CustomerEntity;
import com.sakila.api.service.CustomerService;

@RestController
public class CustomerController {
	private CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	
	
	// 삭제 
	@DeleteMapping("/customer/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int customerId){
		boolean result = customerService.delete(customerId);
		if(result) {
			return new ResponseEntity<String>("삭제 성공", HttpStatus.OK);
		}
		return new ResponseEntity<String>("삭제 실패", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	// 수정
	@PatchMapping("/customer")
	public ResponseEntity<String> update (CustomerDto customerDto){
		
		customerService.update(customerDto);
		return new ResponseEntity<String>("customer수정완료", HttpStatus.OK);
		
	}
	
	
	
	// 입력
	@PostMapping("/customer")
	public ResponseEntity<String>save(CustomerDto customerDto){
		
		customerService.save(customerDto);
		return new ResponseEntity<String>("customer입력완료",HttpStatus.OK);	
	}
	
	// 한행조회
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<CustomerEntity> customerOne(@PathVariable int customerId){
		return new ResponseEntity<CustomerEntity>(customerService.findById(customerId),HttpStatus.OK);
	}
	
	// 전체조회
	@GetMapping("/customer") 
	public ResponseEntity<List<CustomerEntity>>customer(){ 
		return new ResponseEntity<List<CustomerEntity>>(customerService.findAll(),HttpStatus.OK);
	}
	
}
