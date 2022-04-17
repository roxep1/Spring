package com.bashkir.upprakt.repos;

import com.bashkir.upprakt.models.Post
import org.springframework.data.repository.CrudRepository

interface PostRepository : CrudRepository<Post, Long>{
    fun findAllByTitle(title: String): List<Post>
}