package com.poc.restunittest.service;

import com.poc.restunittest.model.entity.EmployeeEntity;
import com.poc.restunittest.model.request.DeleteEmployeeRequest;
import com.poc.restunittest.model.response.DeleteEmployeeResponse;
import com.poc.restunittest.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public class DeleteEmployeeService {

    private final EmployeeRepository employeeRepository;

    public DeleteEmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<DeleteEmployeeResponse> run(DeleteEmployeeRequest deleteEmployeeRequest){
        Optional<EmployeeEntity> retrievedEmployee = employeeRepository.findById(Integer.parseInt(deleteEmployeeRequest.getId()));

        if(retrievedEmployee.isPresent()){
            employeeRepository.deleteById(Integer.parseInt(deleteEmployeeRequest.getId()));
            DeleteEmployeeResponse response = DeleteEmployeeResponse.builder()
                    .response("Deleted employee ID : " + deleteEmployeeRequest.getId() + " successfully")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

    }
}
