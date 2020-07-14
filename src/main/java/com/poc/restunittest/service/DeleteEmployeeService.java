package com.poc.restunittest.service;

import com.poc.restunittest.exception.CustomException;
import com.poc.restunittest.model.entity.EmployeeEntity;
import com.poc.restunittest.model.request.DeleteEmployeeRequest;
import com.poc.restunittest.model.response.DeleteEmployeeResponse;
import com.poc.restunittest.model.response.PatchEmployeeResponse;
import com.poc.restunittest.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class DeleteEmployeeService {

    private final EmployeeRepository employeeRepository;

    public DeleteEmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Object run(DeleteEmployeeRequest deleteEmployeeRequest){
        Optional<EmployeeEntity> retrievedEmployee = employeeRepository.findById(Integer.parseInt(deleteEmployeeRequest.getId()));

        if(retrievedEmployee.isPresent()){
            employeeRepository.deleteById(Integer.parseInt(deleteEmployeeRequest.getId()));
            DeleteEmployeeResponse response = DeleteEmployeeResponse.builder()
                    .response("Deleted employee ID : " + deleteEmployeeRequest.getId() + " successfully")
                    .build();
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
        else{
            CustomException customException = CustomException.builder()
                    .endpoint("/delete-employee/{id}")
                    .message("Could not find an employee")
                    .build();
            return new ResponseEntity<Object>(customException, HttpStatus.NOT_FOUND);
        }

    }
}
