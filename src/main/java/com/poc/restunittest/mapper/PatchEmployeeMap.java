package com.poc.restunittest.mapper;

import com.poc.restunittest.model.entity.EmployeeEntity;
import com.poc.restunittest.model.request.PatchEmployeeRequest;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PatchEmployeeMap {

    PatchEmployeeMap INSTANCE = Mappers.getMapper(PatchEmployeeMap.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EmployeeEntity sourceToTarget(@MappingTarget EmployeeEntity target, PatchEmployeeRequest source);

}
