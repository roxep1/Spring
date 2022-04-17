package com.bashkir.upprakt.repos;

import com.bashkir.upprakt.models.SchoolGroup
import org.springframework.data.repository.CrudRepository

interface GroupRepository : CrudRepository<SchoolGroup, Long>