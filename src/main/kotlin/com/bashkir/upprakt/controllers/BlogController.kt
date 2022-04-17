package com.bashkir.upprakt.controllers

import com.bashkir.upprakt.models.BlogUser
import com.bashkir.upprakt.models.Post
import com.bashkir.upprakt.repos.PostRepository
import com.bashkir.upprakt.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.security.Principal

@Controller
@RequestMapping("/blog")
class BlogController {

    @Autowired
    private lateinit var postRepository: PostRepository
    @Autowired
    private lateinit var userService: UserService

    @GetMapping("")
    fun blogMain(model: Model): String {
        val posts = postRepository.findAll()
        model.addAttribute("posts", posts)
        return "blog_main"
    }

    @GetMapping("/add")
    fun blogAdd(model: Model): String {
        return "blog_add"
    }

    @PostMapping("/add")
    fun blogPostAdd(
        @AuthenticationPrincipal user : BlogUser,
        @RequestParam title: String,
        @RequestParam anons: String,
        @RequestParam full_text: String,
        model: Model
    ): String {
        val post = Post(title, anons, full_text, user)
        postRepository.save(post)
        return "redirect:/blog"
    }

    @PostMapping("/search")
    fun blogFilter(@RequestParam title: String, model: Model): String {
        val result = if (title.isBlank())
            postRepository.findAll().toList()
        else
            postRepository.findAllByTitle(title)
        model.addAttribute("posts", result)
        return "blog_main"
    }

    @GetMapping("/{id}")
    fun blogDetails(@PathVariable("id") id: Long, model: Model): String {
        if (postRepository.existsById(id))
            model.addAttribute("post", returnPost(id))
        else
            return "redirect:/blog"
        return "blog_details"
    }

    @GetMapping("/{id}/edit")
    fun blogEdit(@PathVariable("id") id: Long, model: Model): String {
        if (postRepository.existsById(id))
            model.addAttribute("post", returnPost(id))
        else
            return "redirect:/blog"
        return "blog_edit"
    }

    @PostMapping("/{id}/edit")
    fun blogPostEdit(
        @PathVariable("id") id: Long,
        @RequestParam title: String,
        @RequestParam anons: String,
        @RequestParam full_text: String,
        model: Model
    ): String {
        val post = postRepository.findById(id).orElseThrow { NoSuchElementException() }
        post.title = title
        post.anons = anons
        post.full_text = full_text
        postRepository.save(post)
        return "redirect:/blog"
    }

    @PostMapping("/{id}/delete")
    fun blogPostDelete(@PathVariable("id") id: Long): String {
        if (postRepository.existsById(id))
            postRepository.deleteById(id)
        return "redirect:/blog"
    }

    private fun returnPost(id: Long): ArrayList<Post> {
        val post = postRepository.findById(id)
        val result = arrayListOf<Post>()
        post.ifPresent(result::add)
        return result
    }
}