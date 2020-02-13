package com.luv2code.springboot.cruddemo.dao

import com.luv2code.springboot.cruddemo.entity.Employee
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class EmployeeDAOJpaImpl : EmployeeDAO {

    @PersistenceContext
    private val entityManager: EntityManager? = null

    override fun findAll(): List<Employee>? {
        // create a query
        val query = entityManager?.createQuery("from Employee", Employee::class.java)

        // execute query and get result list and return the results

        return query?.resultList

    }

    override fun findById(id: Int): Employee? {

        // return employee
        return entityManager?.find(Employee::class.java, id)
    }

    override fun save(employee: Employee) {
        // save or update the employee (if id==0 save otherwise update)
        val dbEmployee = entityManager?.merge(employee)

        // update the id from db...so we can get generated id for save/insert
        employee.id = dbEmployee?.id ?: -1
    }

    override fun deleteById(id: Int) {
        val query = entityManager?.createQuery("delete from Employee where id=:employeeId")

        // this will set the :employeeId with arg id within the query
        query?.setParameter("employeeId", id)

        query?.executeUpdate()
    }

}