package com.poc.restunittest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.restunittest.model.entity.EmployeeEntity;
import com.poc.restunittest.model.request.GetEmployeeRequest;
import com.poc.restunittest.repository.EmployeeRepository;
import com.poc.restunittest.service.GetEmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private GetEmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void when_run_controller_with_correct_request_body_should_return_200() throws Exception {
        GetEmployeeRequest getEmployeeRequest = GetEmployeeRequest.builder().build();
        mockMvc.perform(get("/get-employee/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getEmployeeRequest)))
                        .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void when_run_get_all_employee_controller_with_correct_request_body_should_return_200() throws Exception {
        List<EmployeeEntity> mockEmployeeList = new ArrayList<EmployeeEntity>();
        mockEmployeeList.add(EmployeeEntity.builder()
                .id(123)
                .firstName("John")
                .lastName("Smith")
                .birthday(new Date(1594804251))
                .department("Some Department")
                .build());
        when(employeeRepository.findAll()).thenReturn(mockEmployeeList);

        mockMvc.perform(get("/get-all-employees/"))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].firstName").exists())
                .andExpect(jsonPath("$[0].lastName").exists())
                .andExpect(jsonPath("$[0].birthday").exists())
                .andExpect(jsonPath("$[0].department").exists());
    }

}