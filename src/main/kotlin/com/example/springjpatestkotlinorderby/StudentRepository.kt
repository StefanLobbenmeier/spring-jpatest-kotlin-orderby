package com.example.springjpatestkotlinorderby

import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<Student, UUID>
