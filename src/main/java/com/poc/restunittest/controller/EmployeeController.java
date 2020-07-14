package com.poc.restunittest.controller;

import com.poc.restunittest.model.request.DeleteEmployeeRequest;
import com.poc.restunittest.model.request.GetEmployeeRequest;
import com.poc.restunittest.model.request.PatchEmployeeRequest;
import com.poc.restunittest.model.request.PostEmployeeRequest;
import com.poc.restunittest.repository.EmployeeRepository;
import com.poc.restunittest.service.DeleteEmployeeService;
import com.poc.restunittest.service.GetEmployeeService;
import com.poc.restunittest.service.PatchEmployeeService;
import com.poc.restunittest.service.PostEmployeeService;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public Object getEmployee(@PathVariable(value = "id") GetEmployeeRequest getEmployeeRequest){
        return getEmployeeService.run(getEmployeeRequest);
    }

    @GetMapping("/get-all-employee")
    public Object getAllEmployee(){
        return employeeRepository.findAll();
    }

    @PostMapping("/register-employee")
    public Object registerEmployee(@RequestBody @Validated PostEmployeeRequest postEmployeeRequest){
        return postEmployeeService.run(postEmployeeRequest);
    }

    @PatchMapping("/patch-employee/{id}")
    public Object patchEmployee(@PathVariable(value = "id") String id,
                                @RequestBody PatchEmployeeRequest patchEmployeeRequest){
        patchEmployeeRequest.setId(id);
        return patchEmployeeService.run(patchEmployeeRequest);
    }

    @DeleteMapping("/delete-employee/{id}")
    public Object deleteEmployee(@PathVariable(value = "id") DeleteEmployeeRequest deleteEmployeeRequest){
        return deleteEmployeeService.run(deleteEmployeeRequest);
    }
}
