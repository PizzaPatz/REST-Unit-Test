package com.poc.restunittest.controller;

import com.poc.restunittest.model.entity.EmployeeEntity;
import com.poc.restunittest.model.request.DeleteEmployeeRequest;
import com.poc.restunittest.model.request.GetEmployeeRequest;
import com.poc.restunittest.model.request.PatchEmployeeRequest;
import com.poc.restunittest.model.request.PostEmployeeRequest;
import com.poc.restunittest.model.response.DeleteEmployeeResponse;
import com.poc.restunittest.model.response.GetEmployeeResponse;
import com.poc.restunittest.model.response.PatchEmployeeResponse;
import com.poc.restunittest.model.response.PostEmployeeResponse;
import com.poc.restunittest.repository.EmployeeRepository;
import com.poc.restunittest.service.DeleteEmployeeService;
import com.poc.restunittest.service.GetEmployeeService;
import com.poc.restunittest.service.PatchEmployeeService;
import com.poc.restunittest.service.PostEmployeeService;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableJpaRepositories("com.poc.restunittest.repository")
public class EmployeeController {

    private final PostEmployeeService postEmployeeService;
    private final GetEmployeeService getEmployeeService;
    private final PatchEmployeeService patchEmployeeService;
    private final DeleteEmployeeService deleteEmployeeService;
    private final EmployeeRepository employeeRepository;

    public EmployeeController(PostEmployeeService postEmployeeService,
                              GetEmployeeService getEmployeeService,
                              PatchEmployeeService patchEmployeeService,
                              DeleteEmployeeService deleteEmployeeService,
                              EmployeeRepository employeeRepository){
        this.postEmployeeService = postEmployeeService;
        this.getEmployeeService = getEmployeeService;
        this.patchEmployeeService = patchEmployeeService;
        this.deleteEmployeeService = deleteEmployeeService;
        this.employeeRepository = employeeRepository;
    }


    @GetMapping("/get-employee/{id}")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<GetEmployeeResponse> getEmployee (@PathVariable(value = "id") GetEmployeeRequest getEmployeeRequest){
        return getEmployeeService.run(getEmployeeRequest);
    }

    @GetMapping("/get-all-employees")
    public ResponseEntity<List<EmployeeEntity>> getAllEmployee(){
        return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/post-employee")
    public ResponseEntity<PostEmployeeResponse> registerEmployee(@RequestBody @Validated PostEmployeeRequest postEmployeeRequest){
        return postEmployeeService.run(postEmployeeRequest);
    }

    @PatchMapping("/patch-employee/{id}")
    public ResponseEntity<PatchEmployeeResponse> patchEmployee(@PathVariable(value = "id") String id,
                                                               @RequestBody PatchEmployeeRequest patchEmployeeRequest){
        patchEmployeeRequest.setId(id);
        return patchEmployeeService.run(patchEmployeeRequest);
    }

    @DeleteMapping("/delete-employee/{id}")
    public ResponseEntity<DeleteEmployeeResponse> deleteEmployee(@PathVariable(value = "id") DeleteEmployeeRequest deleteEmployeeRequest){
        return deleteEmployeeService.run(deleteEmployeeRequest);
    }

}
