package com.bashkir.upprakt.repos;

import com.bashkir.upprakt.models.BlogUser
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<BlogUser, Long> {
    fun findByUsername(username: String) : BlogUser?
}