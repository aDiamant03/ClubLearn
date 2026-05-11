package school.project.teamproject.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import school.project.teamproject.dto.StudentCreateRequest
import school.project.teamproject.model.Student
import school.project.teamproject.repository.StudentRepository
import java.util.*

@ExtendWith(MockitoExtension::class)
class StudentServiceTest {

    @Mock
    private lateinit var studentRepository: StudentRepository

    @InjectMocks
    private lateinit var studentService: StudentService

    @Test
    fun `create should save student and return with generated id`() {
        val request = StudentCreateRequest(
            name = "Иван",
            surname = "Петров",
            email = "ivan@example.com",
            password = "secret",
            grade = 8
        )
        val savedStudent = Student(
            id = 1L,
            name = request.name,
            surname = request.surname,
            email = request.email,
            password = request.password,
            grade = request.grade
        )
        `when`(studentRepository.save(any(Student::class.java))).thenReturn(savedStudent)
        val result = studentService.create(request)
        assertThat(result.id).isEqualTo(1L)
        assertThat(result.name).isEqualTo("Иван")
        assertThat(result.email).isEqualTo("ivan@example.com")
        verify(studentRepository, times(1)).save(any(Student::class.java))
    }



    @Test
    fun `getById should return student when exists`() {
        val student = Student(id = 1L, name = "Ivan", surname = "Ivanov", email = "i@i.com", password = "pass", grade = 11)
        `when`(studentRepository.findById(1L)).thenReturn(Optional.of(student))

        val result = studentService.getById(1L)

        assertThat(result).isNotNull
        assertThat(result?.id).isEqualTo(1L)
        assertThat(result?.name).isEqualTo("Ivan")
    }

    @Test
    fun `getById should return null when not exists`() {
        `when`(studentRepository.findById(99L)).thenReturn(Optional.empty())

        val result = studentService.getById(99L)

        assertThat(result).isNull()
    }

    @Test
    fun `delete should return false when student does not exist`() {
        `when`(studentRepository.existsById(99L)).thenReturn(false)

        val result = studentService.delete(99L)

        assertThat(result).isFalse()
        verify(studentRepository, never()).deleteById(any())
    }
}