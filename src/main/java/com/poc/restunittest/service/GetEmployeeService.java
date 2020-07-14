package com.poc.restunittest.service;

import com.poc.restunittest.exception.CustomException;
import com.poc.restunittest.model.entity.EmployeeEntity;
import com.poc.restunittest.model.request.GetEmployeeRequest;
import com.poc.restunittest.model.response.GetEmployeeResponse;
import com.poc.restunittest.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetEmployeeService {

    private final EmployeeRepository employeeRepository;

    public GetEmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Object run(GetEmployeeRequest employeeRequest){
        Optional<EmployeeEntity> retrievedEmployee = employeeRepository.findById(Integer.parseInt(employeeRequest.getId()));
        if(retrievedEmployee.isPresent()){
            return GetEmployeeResponse.builder()
                    .id(retrievedEmployee.get().getId())
                    .firstName(retrievedEmployee.get().getFirstName())
                    .lastName(retrievedEmployee.get().getLastName())
                    .department(retrievedEmployee.get().getDepartment())
                    .birthday(retrievedEmployee.get().getBirthday())
                    .build();
        }
        else{
            CustomException customException = CustomException.builder()
                    .endpoint("/get-employee/{id}")
                    .message("Could not find an employee")
                    .build();
            return new ResponseEntity<Object>(customException, HttpStatus.NOT_FOUND);
        }
    }
}
