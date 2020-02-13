package com.luv2code.springboot.cruddemo.service

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO
import com.luv2code.springboot.cruddemo.entity.Employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
// Qualifier will tell the service which implementation of the employeeDAO to use
class EmployeeServiceImpl @Autowired
constructor(@Qualifier("employeeDAOJpaImpl") theEmployeeDAO: EmployeeDAO) : EmployeeService {
    private val employeeDAO = theEmployeeDAO

    @Override
    @Transactional
    override fun findAll(): List<Employee>? {
        return employeeDAO.findAll()
    }

    @Override
    @Transactional
    override fun findById(id: Int): Employee? {
        return employeeDAO.findById(id)
    }

    @Override
    @Transactional
    override fun save(employee: Employee) {
        employeeDAO.save(employee)
    }

    @Override
    @Transactional
    override fun deleteById(id: Int) {
        employeeDAO.deleteById(id)
    }
}