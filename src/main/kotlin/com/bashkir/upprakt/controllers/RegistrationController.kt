package com.bashkir.upprakt.controllers

import com.bashkir.upprakt.models.Role
import com.bashkir.upprakt.models.BlogUser
import com.bashkir.upprakt.repos.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import java.util.*
import javax.validation.Valid

@Controller
class RegistrationController {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @GetMapping("/registration")
    fun registration(): String {
        return "registration"
    }

    @PostMapping("/registration")
    fun addUser(@ModelAttribute("user") @Valid user: BlogUser, binding: BindingResult, model: Model): String {
        if(binding.hasErrors())
            return "/registration"
        val userFromDb = userRepository.findByUsername(user.username!!)
        if (userFromDb != null) {
            model.addAttribute("message", "Такой пользователь уже существует!!!")
            return "registration"
        }
        user.active = true
        user.roles = Collections.singleton(Role.USER)
        user.password = passwordEncoder.encode(user.password)
        userRepository.save(user)
        return "redirect:/login"
    }
}