package com.poc.restunittest.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetEmployeeResponse implements Serializable {

    private Integer id;

    private String firstName;

    private String lastName;

    private String department;

    private Date birthday;

}
