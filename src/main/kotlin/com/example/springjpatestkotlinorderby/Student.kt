package com.example.springjpatestkotlinorderby

import java.time.ZonedDateTime
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.OrderBy
import org.hibernate.annotations.Type
import org.hibernate.annotations.UpdateTimestamp


@Entity
@Table(name = "STUDENT")
class Student(
    @Id @GeneratedValue @Type(type = "pg-uuid") var id: UUID? = null,
    var name: String,
    @OneToMany(
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        mappedBy = "student"
    ) @OrderBy(clause = "date DESC") var grades: MutableList<Grade> = mutableListOf(),
    @CreationTimestamp @Column(updatable = false) val creationDate: ZonedDateTime? = null,
    @UpdateTimestamp val modificationDate: ZonedDateTime? = null,
)
