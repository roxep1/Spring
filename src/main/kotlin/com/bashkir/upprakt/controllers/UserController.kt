package com.bashkir.upprakt.controllers

import com.bashkir.upprakt.models.BlogUser
import com.bashkir.upprakt.models.Role
import com.bashkir.upprakt.repos.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
class UserController {
    @Autowired
    private lateinit var userRepository: UserRepository

    @GetMapping
    fun userList(model: Model): String{
        model.addAttribute("users", userRepository.findAll())
        return "userList"
    }

    @GetMapping("/{id}/edit")
    fun adminEdit(@PathVariable("id") id: Long, model: Model): String{
        val user = userRepository.findById(id)
        val res = arrayListOf<BlogUser>()
        user.ifPresent(res::add)
        model.addAttribute("user", res)
        model.addAttribute("roles", Role.values())
        return "userEdit"
    }

    @PostMapping
    fun userSave(
        @RequestParam username : String,
        @RequestParam(name = "roles[]", required = false)roles : Array<String>?,
        @RequestParam("userId") user : BlogUser
    ): String {
        user.username = username
        user.roles?.clear()
        roles?.forEach { user.roles?.add(Role.valueOf(it))}
        userRepository.save(user)
        return "redirect:/admin"
    }
}