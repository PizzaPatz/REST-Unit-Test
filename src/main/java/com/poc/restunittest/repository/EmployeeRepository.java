package com.poc.restunittest.repository;

import com.poc.restunittest.model.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    Optional<EmployeeEntity> findById(Integer id);

    List<EmployeeEntity> findAll();

    void deleteById(Integer id);

}
