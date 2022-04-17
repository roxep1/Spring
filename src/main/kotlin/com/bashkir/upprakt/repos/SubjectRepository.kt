package com.bashkir.upprakt.repos;

import com.bashkir.upprakt.models.Subject
import org.springframework.data.repository.CrudRepository

interface SubjectRepository : CrudRepository<Subject, Long>