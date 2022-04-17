package com.bashkir.upprakt.models

import javax.persistence.*

@Entity
class SchoolGroup (){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    var name : String? = null
    var countOfStudent : Int? = null

    @ManyToMany
    @JoinTable(name="group_subject",
                joinColumns = [JoinColumn(name="group_id")],
                inverseJoinColumns = [JoinColumn(name = "subject_id")])
    var subjects : List<Subject>? = null

    constructor(name: String?, countOfStudent: Int?) : this() {
        this.name = name
        this.countOfStudent = countOfStudent
    }
}