package com.poc.restunittest.service;

import com.poc.restunittest.model.entity.EmployeeEntity;
import com.poc.restunittest.model.request.PostEmployeeRequest;
import com.poc.restunittest.model.response.PostEmployeeResponse;
import com.poc.restunittest.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostEmployeeService {

    private final EmployeeRepository employeeRepository;

    public PostEmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<PostEmployeeResponse> run(PostEmployeeRequest postEmployeeRequest){
        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .firstName(postEmployeeRequest.getFirstName())
                .lastName(postEmployeeRequest.getLastName())
                .department(postEmployeeRequest.getDepartment())
                .birthday(postEmployeeRequest.getBirthday())
                .build();

        employeeRepository.save(employeeEntity);

        PostEmployeeResponse response = PostEmployeeResponse.builder()
                .response("Employee has been registered")
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
