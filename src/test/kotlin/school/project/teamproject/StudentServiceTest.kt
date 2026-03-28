import school.project.teamproject.model.Student
import school.project.teamproject.service.StudentService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.assertj.core.api.Assertions.assertThat

class StudentServiceTest{
    private lateinit var studentService: StudentService

    @BeforeEach
    fun setUp() {
        studentService = StudentService()
    }

    @Test
    fun `should increment id when creating students`() {
        val s1 = studentService.create(Student(name = "Ivan", surname = " Ivanov", email = "Ivanov@gmail.com", password = "eufnwd", id = 1))
        val s2 = studentService.create(Student(name = "Oleg", surname = " Ivanov", email = "OlegIvanov@gmail.com", password = "eufnwd", id = 2))

        assertThat(s1.id).isEqualTo(1)
        assertThat(s2.id).isEqualTo(2)
        assertThat(studentService.getAll()).hasSize(2)
    }
    @Test
    fun `should get by id retutn null if there is no student`(){
        val s1 = studentService.create(Student(name = "Ivan", surname = " Ivanov", email = "Ivanov@gmail.com", password = "eufnwd", id = 1))
        val idi = s1.id
        studentService.delete(s1.id)
        val result = studentService.getById(idi)
        assertThat(result).isNull()
    }
    @Test
    fun `should return correct student by id`() {
        val s1 = studentService.create(Student(name = "Ivan", surname = " Ivanov", email = "Ivanov@gmail.com", password = "eufnwd", id = 1))
        val found = studentService.getById(s1.id)

        assertThat(found).isEqualTo(s1)
        assertThat(found?.name).isEqualTo("Ivan")
    }

}