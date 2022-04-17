package com.bashkir.upprakt.models

import javax.persistence.*

@Entity
class Post() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    var title: String? = null
    var anons: String? = null
    var full_text: String? = null
    var views: Int = 0

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    var author : BlogUser? = null

    constructor(title: String, anons: String, full_text: String, author: BlogUser) : this() {
        this.author = author
        this.title = title
        this.anons = anons
        this.full_text = full_text
    }
}
