package com.luv2code.springboot.cruddemo.dao

import com.luv2code.springboot.cruddemo.entity.Employee
import org.hibernate.Session
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


@Repository
class EmployeeDAOHibernateImpl : EmployeeDAO {
    // Inject entity manager
    @PersistenceContext
    val entityManager: EntityManager? = null

    override fun findAll(): List<Employee>? {
        return entityManager?.unwrap(Session::class.java)?.session?.createQuery("FROM Employee", Employee::class.java)?.resultList
    }

    override fun findById(id: Int): Employee? {
        return entityManager?.unwrap(Session::class.java)?.get(Employee::class.java, id)
    }

    override fun save(employee: Employee) {
        // if id=0 then save otherwise update
        entityManager?.unwrap(Session::class.java)?.saveOrUpdate(employee)
    }

    override fun deleteById(id: Int) {
        entityManager?.unwrap(Session::class.java)?.let { session ->
            // do not use string templates for search variable (employeeId)
            val query = session.createQuery("delete from Employee where id=:employeeId")
            query.setParameter("employeeId", id)
            query.executeUpdate()
        }
    }
}