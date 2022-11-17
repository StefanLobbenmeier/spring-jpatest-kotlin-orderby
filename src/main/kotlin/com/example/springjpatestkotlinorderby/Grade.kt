package com.example.springjpatestkotlinorderby

import java.math.BigDecimal
import java.time.LocalDate
import java.time.ZonedDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.Type
import org.hibernate.annotations.UpdateTimestamp


@Entity
@Table(name = "GRADE")
class Grade(
    @Id @GeneratedValue @Type(type = "pg-uuid") var id: UUID?,
    var date: LocalDate,
    var value: BigDecimal,
    @CreationTimestamp @Column(updatable = false) val creationDate: ZonedDateTime?,
    @UpdateTimestamp val modificationDate: ZonedDateTime?,
) {
    @ManyToOne
    @JoinColumn(name = "student_id")
    var student: Student? = null
}
