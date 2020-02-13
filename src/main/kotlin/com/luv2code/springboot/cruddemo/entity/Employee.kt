package com.luv2code.springboot.cruddemo.entity

import javax.persistence.*


@Entity
@Table(name = "employee")
data class Employee(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var id: Int = 0,
        @Column(name = "first_name")
        var firstName: String,
        @Column(name = "last_name")
        var lastName: String,
        @Column(name = "email")
        var email: String
)

