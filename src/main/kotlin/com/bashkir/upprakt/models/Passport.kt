package com.bashkir.upprakt.models

import javax.persistence.*

@Entity
class Passport{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    var series: String? = null
    var number: String? = null

    @OneToOne(optional = false, mappedBy = "passport")
    var owner: BlogUser? = null
}