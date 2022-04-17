package com.bashkir.upprakt.services

import com.bashkir.upprakt.models.BlogUser
import com.bashkir.upprakt.repos.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService : UserDetailsService {
    @Autowired
    private lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String): BlogUser? = userRepository.findByUsername(username)
}