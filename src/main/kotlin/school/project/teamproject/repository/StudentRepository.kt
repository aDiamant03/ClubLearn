package school.project.teamproject.repository

import org.springframework.data.jpa.repository.JpaRepository
import school.project.teamproject.model.Student

interface StudentRepository : JpaRepository<Student, Long>