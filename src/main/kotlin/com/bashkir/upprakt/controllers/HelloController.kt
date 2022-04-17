package com.bashkir.upprakt.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/firsts")
class HelloController {
    @GetMapping("/hello")
    fun sayHello(
        @RequestParam("name") name: String,
        @RequestParam("surname", required = false) surname: String?,
        model: Model
    ): String {
        model.addAttribute("message", "Hello, $name $surname")
        return "hello"
    }

    @GetMapping("/calculator")
    fun calculate(
        @RequestParam("a") a: Int,
        @RequestParam("b") b: Int,
        @RequestParam("action") action: String,
        model: Model
    ): String {
        model.addAttribute(
            "result",
            when (action) {
                "multiplication" -> a * b
                "division" -> a / b
                "subtraction" -> a - b
                "addition" -> a + b
                else -> 0
            }
        )
        return "calculate"
    }
}