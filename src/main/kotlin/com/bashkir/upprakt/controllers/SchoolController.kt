package com.bashkir.upprakt.controllers

import com.bashkir.upprakt.models.Student
import com.bashkir.upprakt.repos.GroupRepository
import com.bashkir.upprakt.repos.StudentRepository
import com.bashkir.upprakt.repos.SubjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/school")
class SchoolController {

    @Autowired
    private lateinit var groupRepository: GroupRepository

    @Autowired
    private lateinit var studentRepository: StudentRepository

    @Autowired
    private lateinit var subjectRepository: SubjectRepository

    @GetMapping("")
    fun mainPage(model: Model): String {
        model.addAttribute("students", studentRepository.findAll())
        model.addAttribute("groups", groupRepository.findAll())
        model.addAttribute("subjects", subjectRepository.findAll())
        return "school_main"
    }

    @GetMapping("{id}")
    fun studentDetail(@PathVariable("id") id: Long, model: Model): String {
        if (!studentRepository.existsById(id))
            return "redirect:/school"
        val student = studentRepository.findById(id)
        val result = arrayListOf<Student>()
        student.ifPresent(result::add)
        model.addAttribute("student", result)
        return "school_student"
    }

    @GetMapping("{id}/edit")
    fun studentEdit(@PathVariable("id") id: Long, model: Model): String {
        studentRepository.findById(id).ifPresent {
            model.addAttribute("student", it)
        }
        return "school_edit"
    }

    @PostMapping("{id}/edit")
    fun studentPostEdit(
        @PathVariable("id") id: Long,
        @RequestParam name: String,
        @RequestParam phone: String,
        @RequestParam address: String,
        @RequestParam old: Int,
        @RequestParam birthday: String,
        @RequestParam groupId : Long
    ): String {
        val student = studentRepository.findById(id).orElseThrow { NoSuchElementException() }
        student.address = address
        student.birthday = birthday
        student.old = old
        student.phone = phone
        student.groupId = groupRepository.findById(groupId).orElseGet {student.groupId}
        student.name = name
        studentRepository.save(student)
        return "redirect:/school"
    }

    @PostMapping("{id}/delete")
    fun studentDelete(
        @PathVariable("id") id : Long
    ): String{
        studentRepository.deleteById(id)
        return "redirect:/school"
    }
}