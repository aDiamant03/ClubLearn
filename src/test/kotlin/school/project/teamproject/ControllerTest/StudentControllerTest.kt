package school.project.teamproject.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import school.project.teamproject.dto.StudentCreateRequest
import school.project.teamproject.dto.StudentResponse
import school.project.teamproject.model.Student
import school.project.teamproject.service.StudentService
import org.springframework.test.context.bean.override.mockito.MockitoBean
@WebMvcTest(StudentController::class)
class StudentControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockitoBean
    private lateinit var studentService: StudentService



    @Test
    fun `getAllStudents should return list`() {
        val students = listOf(
            Student(1L, "Ivan", "Petrov", "ivan@mail.com", "pass", 9),
            Student(2L, "Maria", "Sidorova", "maria@mail.com", "pass", 10)
        )
        `when`(studentService.getAll()).thenReturn(students)

        mockMvc.perform(get("/api/students"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].name").value("Ivan"))
            .andExpect(jsonPath("$[1].name").value("Maria"))
            .andExpect(jsonPath("$[0].password").doesNotExist())
    }

    @Test
    fun `deleteStudent should return 204 when student exists`() {
        `when`(studentService.delete(1L)).thenReturn(true)

        mockMvc.perform(delete("/api/students/1"))
            .andExpect(status().isNoContent())
            .andExpect(content().string(""))
    }

    @Test
    fun `deleteStudent should return 404 when student not found`() {
        `when`(studentService.delete(99L)).thenReturn(false)

        mockMvc.perform(delete("/api/students/99"))
            .andExpect(status().isNotFound())
    }
}