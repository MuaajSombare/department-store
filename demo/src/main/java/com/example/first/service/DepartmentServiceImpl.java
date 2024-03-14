package com.example.first.service;

import com.example.first.entity.Department;
import com.example.first.error.DepartmentNotFoundException;
import com.example.first.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department) ;
    }

    @Override
    public List<Department> getDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if(!department.isPresent()){
            throw new DepartmentNotFoundException("department not found");
        }
        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);

    }


    @Override
    public Department updateDepartmentById(Long departmentId, Department department) {
        Department db = departmentRepository.findById(departmentId).get();
        if(Objects.nonNull(department.getDepartmentName())  && !"".equalsIgnoreCase(department.getDepartmentName())){
            db.setDepartmentName(department.getDepartmentName());
        }
        if(Objects.nonNull(department.getDepartmentcode())  && !"".equalsIgnoreCase(department.getDepartmentcode())){
            db.setDepartmentcode(department.getDepartmentcode());
        }
        if(Objects.nonNull(department.getDepartmentAddress())  && !"".equalsIgnoreCase(department.getDepartmentAddress())){
            db.setDepartmentAddress(department.getDepartmentAddress());
        }
        return departmentRepository.save(db);
    }

    @Override
    public Department getDepartmentByName(String departmentName) {
        return departmentRepository.findBydepartmentNameIgnoreCase(departmentName);
    }



}
