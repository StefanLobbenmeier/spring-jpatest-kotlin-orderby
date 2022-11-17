package com.example.springjpatestkotlinorderby

import assertk.assertThat
import assertk.assertions.isDataClassEqualTo
import assertk.assertions.isEqualTo
import java.math.BigDecimal
import java.time.LocalDate
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SpringJpatestKotlinOrderbyApplicationTests {

    @Autowired
    lateinit var studentRepository: StudentRepository

    @Test
    fun contextLoads() {
    }

    @Test
    fun gradesAreRetrievedInOrder() {
        val student = studentRepository.save(Student(name = "John"))

        val gradeValue = BigDecimal.valueOf(50)
        student.grades.add(Grade(date = LocalDate.of(2021, 1, 1), value = gradeValue))
        student.grades.add(Grade(date = LocalDate.of(2022, 1, 1), value = gradeValue))
        student.grades.add(Grade(date = LocalDate.of(2020, 1, 1), value = gradeValue))

        val studentWithGrades = studentRepository.save(student)

        assertThat(studentWithGrades.grades[0].date.year).isEqualTo(2022)
        assertThat(studentWithGrades.grades[1].date.year).isEqualTo(2021)
        assertThat(studentWithGrades.grades[2].date.year).isEqualTo(2020)
    }

}
