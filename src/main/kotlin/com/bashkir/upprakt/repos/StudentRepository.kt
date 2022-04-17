package com.bashkir.upprakt.repos;

import com.bashkir.upprakt.models.Student
import org.springframework.data.repository.CrudRepository

interface StudentRepository : CrudRepository<Student, Long>