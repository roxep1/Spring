package com.bashkir.upprakt.models

import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Entity
class BlogUser : UserDetails {

    @OneToOne(optional = false, cascade = [CascadeType.ALL])
    @JoinColumn(name="passport_id")
    var passport : Passport? = null

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 2, max = 30, message = "Логин должен быть от 2 до 30 символов")
    @get:JvmName("getBlogUserUsername")
    var username: String? = null

    @NotBlank(message = "Поле не может быть пустым")
    @Size(min = 2, max = 30, message = "Пароль должен быть от 6 до 100 символов")
    @get:JvmName("getBlogUserPassword")
    var password: String? = null
    var active: Boolean? = null

    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id")])
    @Enumerated(EnumType.STRING)
    var roles: MutableSet<Role> = mutableSetOf()

    override fun getAuthorities(): Set<Role> = roles

    override fun getPassword(): String? = password

    override fun getUsername(): String? = username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = active!!
}