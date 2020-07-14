package com.poc.restunittest.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostEmployeeRequest implements Serializable {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String department;

    @NotNull
    private Date birthday;

}
