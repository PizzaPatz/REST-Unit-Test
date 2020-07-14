package com.poc.restunittest.service;

import com.poc.restunittest.exception.CustomException;
import com.poc.restunittest.mapper.PatchEmployeeMap;
import com.poc.restunittest.model.entity.EmployeeEntity;
import com.poc.restunittest.model.request.PatchEmployeeRequest;
import com.poc.restunittest.model.response.PatchEmployeeResponse;
import com.poc.restunittest.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatchEmployeeService {

    private final EmployeeRepository employeeRepository;

    public PatchEmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Object run(PatchEmployeeRequest patchEmployeeRequest){

        Optional<EmployeeEntity> retrievedEmployee = employeeRepository.findById(Integer.parseInt(patchEmployeeRequest.getId()));

        if(retrievedEmployee.isPresent()){
            PatchEmployeeMap.INSTANCE.sourceToTarget(retrievedEmployee.get(), patchEmployeeRequest);
            employeeRepository.save(retrievedEmployee.get());
            PatchEmployeeResponse response = PatchEmployeeResponse.builder()
                    .response("Patched employee ID : " + patchEmployeeRequest.getId() + " successfully")
                    .build();
            return new ResponseEntity<Object>(response, HttpStatus.OK);
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
