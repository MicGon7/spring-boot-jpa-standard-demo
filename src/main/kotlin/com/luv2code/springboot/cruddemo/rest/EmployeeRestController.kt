package com.luv2code.springboot.cruddemo.rest

import com.luv2code.springboot.cruddemo.entity.Employee
import com.luv2code.springboot.cruddemo.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class EmployeeRestController
@Autowired constructor(theEmployeeService: EmployeeService) {
    // quick and dirty: inject employee dao
    var employeeService: EmployeeService? = theEmployeeService

    // expose "/employees" and return list of employees
    @GetMapping("/employees")
    fun findAll(): List<Employee>? {
        return employeeService?.findAll()
    }

    // expose "/employees/{id}" and return a single employee by id
    @GetMapping("/employees/{employeeId}")
    fun findById(@PathVariable employeeId: Int): Employee? {
        return employeeService?.findById(employeeId) ?: throw RuntimeException("Employee not found - $employeeId")
    }

    // add mapping for POST /employees - add new employee
    @PostMapping("/employees")
    fun addEmployee(@RequestBody theEmployee: Employee): Employee {
        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of a new item ... instead of update
        theEmployee.id = 0
        employeeService?.save(theEmployee)

        return theEmployee
    }

    // add mapping for PUT /employees = update existing employee
    @PutMapping("/employees")
    fun updateEmployee(@RequestBody theEmployee: Employee): Employee {
        employeeService?.save(theEmployee)
        return theEmployee
    }

    // add mapping for DELETE /employees/{employeeId} - delete employee
    @DeleteMapping("/employees/{employeeId}")
    fun deleteEmployee(@PathVariable employeeId: Int): String {
        // throw exception if id not found
        employeeService?.findById(employeeId)
                ?: throw RuntimeException("Employee id not found - $employeeId")

        employeeService?.deleteById(employeeId)
        return "Deleted employee id - $employeeId"
    }


}