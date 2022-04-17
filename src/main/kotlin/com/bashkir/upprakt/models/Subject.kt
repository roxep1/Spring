package com.bashkir.upprakt.models

import javax.persistence.*

@Entity
class Subject(){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    var name: String? = null
    var duration : String? = null

    @ManyToMany
    @JoinTable(name="group_subject",
        joinColumns = [JoinColumn(name="subject_id")],
        inverseJoinColumns = [JoinColumn(name = "group_id")])
    var subjects : List<Subject>? = null

    constructor(name: String?, duration: String?) : this() {
        this.name = name
        this.duration = duration
    }
}