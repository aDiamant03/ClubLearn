package school.project.teamproject.mapper

import school.project.teamproject.dto.StudentResponse
import school.project.teamproject.model.Student

fun Student.toResponse(): StudentResponse = StudentResponse(
    id = this.id,
    name = this.name,
    surname = this.surname,
    email = this.email,
    grade = this.grade
)