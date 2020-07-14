package com.poc.restunittest.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchEmployeeRequest implements Serializable {

    private String id;

    private String firstName;

    private String lastName;

    private String department;

    private Date birthday;

}
