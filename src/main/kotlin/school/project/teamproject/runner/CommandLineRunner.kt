package school.project.teamproject.runner
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import org.springframework.stereotype.Service
import school.project.teamproject.model.Teacher
import school.project.teamproject.model.Student
import school.project.teamproject.model.Task
import school.project.teamproject.model.Atempt
import school.project.teamproject.model.Admin
@Service
class ClubLearnService {
    private val log = LoggerFactory.getLogger(ClubLearnService::class.java)

    fun registerTeacher(teacher: Teacher) {
        log.info("Teacher registered: ${teacher.name} ${teacher.surname}")
    }

    fun registerStudent(student: Student) {
        log.info("Student registered: ${student.name} ${student.surname}")
    }

    fun createTask(task: Task) {
        log.info("Task created: ${task.title}, difficulty=${task.difficulty}")
    }

    fun submitAttempt(attempt: Atempt) {
        log.info("Attempt submitted by ${attempt.student.name} for task ${attempt.task.title}")
    }

    fun registerAdmin(admin: Admin) {
        log.info("Admin registered: ${admin.name} ${admin.surname}")
    }
}
@Component
class AppStartupRunner(
    private val service: ClubLearnService
) : CommandLineRunner {

    private val log = LoggerFactory.getLogger(AppStartupRunner::class.java)

    override fun run(vararg args: String?) {
        println("Application started")
        log.info("Application startup logic running")
        val teacher = Teacher(
            id = 1,
            name = "Dima",
            surname = "Anchukov",
            email = "dima@school.com",
            password = "1234",
            subjects = listOf("Math", "Physics")
        )
        val student = Student(
            id = 1,
            name = "Misha",
            surname = "Medvedev",
            email = "misha@student.com",
            password = "pass",
            grade = 10
        )
        val task = Task(
            id = 1,
            title = "Solve equation",
            description = "Solve x + 2 = 5",
            answer = "3",
            difficulty = "EASY",
            subject = "Math",
            topic = "Linear equations",
            points = 10,
            hint = "Move 2 to the other side"
        )

        val admin = Admin(
            id = 1,
            name = "Super",
            surname = "Admin",
            email = "admin@club.com",
            password = "admin"
        )

        val attempt = Atempt(
            id = 1,
            student = student,
            task = task,
            submittedAnswer = "3",
            status = true,
            score = 10,
            hintsUsed = 0,
            timeSpentSeconds = 25,
            solvedAt = LocalDateTime.now()
        )
        service.registerTeacher(teacher)
        service.registerStudent(student)
        service.createTask(task)
        service.registerAdmin(admin)
        service.submitAttempt(attempt)
        println("All objects created and processed")
        log.info("Startup data successfully initialized")
    }
}

