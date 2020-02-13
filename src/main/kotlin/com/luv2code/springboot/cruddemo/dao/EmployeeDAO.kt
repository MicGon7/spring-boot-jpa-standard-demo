package com.luv2code.springboot.cruddemo.dao

import com.luv2code.springboot.cruddemo.entity.Employee

interface EmployeeDAO {
    fun findAll(): List<Employee>?

    fun findById(id: Int): Employee?

    fun save(employee: Employee)

    fun deleteById(id: Int)
}