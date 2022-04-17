package com.bashkir.upprakt.models

import javax.persistence.*

@Entity
class Student() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    var name: String? = null

    var phone: String? = null

    var address: String? = null
    var old: Int? = null
    var birthday: String? = null

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "group_id")
    var groupId: SchoolGroup? = null

    constructor(
        name: String?,
        phone: String?,
        address: String?,
        old: Int?,
        birthday: String?,
        group: SchoolGroup?
    ) : this() {
        this.name = name
        this.phone = phone
        this.address = address
        this.old = old
        this.birthday = birthday
        this.groupId = group
    }
}