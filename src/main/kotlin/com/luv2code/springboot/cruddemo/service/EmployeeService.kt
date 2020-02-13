package com.luv2code.springboot.cruddemo.service

import com.luv2code.springboot.cruddemo.entity.Employee

interface EmployeeService {
    fun findAll(): List<Employee>?

    fun findById(id: Int): Employee?

    fun save(employee: Employee)

    fun deleteById(id: Int)
}