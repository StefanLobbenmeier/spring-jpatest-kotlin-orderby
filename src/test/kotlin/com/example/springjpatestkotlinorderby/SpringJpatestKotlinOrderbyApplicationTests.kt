package com.example.springjpatestkotlinorderby

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import java.math.BigDecimal
import java.time.LocalDate
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class SpringJpatestKotlinOrderbyApplicationTests {

    @Autowired
    lateinit var studentRepository: StudentRepository

    @Test
    fun contextLoads() {
    }

    @Test
    fun gradesAreInOrderAfterSave() {
        val student = studentRepository.save(Student(name = "John"))

        val gradeValue = BigDecimal.valueOf(50)
        student.grades.add(Grade(date = LocalDate.of(2021, 1, 1), value = gradeValue))
        student.grades.add(Grade(date = LocalDate.of(2022, 1, 1), value = gradeValue))
        student.grades.add(Grade(date = LocalDate.of(2020, 1, 1), value = gradeValue))

        val savedStudent = studentRepository.save(student)

        assertAll {
            assertThat(savedStudent.grades[0].date.year).isEqualTo(2022)
            assertThat(savedStudent.grades[1].date.year).isEqualTo(2021)
            assertThat(savedStudent.grades[2].date.year).isEqualTo(2020)
        }
    }

    @Test
    @Transactional
    fun gradesAreRetrievedInOrder() {
        val student = studentRepository.save(Student(name = "John"))

        val gradeValue = BigDecimal.valueOf(50)
        student.grades.add(Grade(date = LocalDate.of(2021, 1, 1), value = gradeValue))
        student.grades.add(Grade(date = LocalDate.of(2022, 1, 1), value = gradeValue))
        student.grades.add(Grade(date = LocalDate.of(2020, 1, 1), value = gradeValue))

        val savedStudent = studentRepository.save(student)

        val retrievedStudent = studentRepository.getReferenceById(savedStudent.id!!)

        assertAll {
            assertThat(retrievedStudent.grades[0].date.year).isEqualTo(2022)
            assertThat(retrievedStudent.grades[1].date.year).isEqualTo(2021)
            assertThat(retrievedStudent.grades[2].date.year).isEqualTo(2020)
        }
    }

}
