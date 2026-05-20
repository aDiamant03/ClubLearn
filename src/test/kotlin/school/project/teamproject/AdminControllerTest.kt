package school.project.teamproject

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import school.project.teamproject.controller.AdminController
import school.project.teamproject.model.Admin
import school.project.teamproject.service.AdminService

class AdminControllerTest {

    private lateinit var adminService: AdminService
    private lateinit var adminController: AdminController

    @BeforeEach
    fun setUp() {
        adminService = AdminService()
        adminController = AdminController(adminService)
    }

    @Test
    fun `should create admin`() {
        val admin = Admin(
            id = 0,
            name = "Dima",
            surname = "Anchukov",
            email = "dva01082009@gmail.com",
            password = "1234"
        )

        val response = adminController.createAdmin(admin)

        assertThat(response.statusCode).isEqualTo(HttpStatus.CREATED)
        assertThat(response.body?.id).isEqualTo(1)
        assertThat(response.body?.name).isEqualTo("Dima")
        assertThat(response.body?.surname).isEqualTo("Anchukov")
        assertThat(response.body?.email).isEqualTo("dva01082009@gmail.com")
    }

    @Test
    fun `should get all admins`() {
        val admin = Admin(
            id = 0,
            name = "Dima",
            surname = "Anchukov",
            email = "dva01082009@gmail.com",
            password = "1234"
        )

        adminController.createAdmin(admin)

        val admins = adminController.getAllAdmins()

        assertThat(admins).hasSize(1)
        assertThat(admins[0].name).isEqualTo("Dima")
        assertThat(admins[0].surname).isEqualTo("Anchukov")
    }

    @Test
    fun `should get admin by id`() {
        val admin = Admin(
            id = 0,
            name = "Dima",
            surname = "Anchukov",
            email = "dva01082009@gmail.com",
            password = "1234"
        )

        val createdAdmin = adminController.createAdmin(admin).body!!

        val response = adminController.getAdminById(createdAdmin.id)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body?.email).isEqualTo("dva01082009@gmail.com")
    }

    @Test
    fun `should return not found when admin does not exist`() {
        val response = adminController.getAdminById(100)

        assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }

    @Test
    fun `should update admin`() {
        val admin = Admin(
            id = 0,
            name = "Dima",
            surname = "Anchukov",
            email = "dva01082009@gmail.com",
            password = "1234"
        )

        val createdAdmin = adminController.createAdmin(admin).body!!

        val newAdmin = Admin(
            id = 0,
            name = "Misha",
            surname = "Epstein",
            email = "misha_epstein@gmail.com",
            password = "9999"
        )

        val response = adminController.updateAdmin(createdAdmin.id, newAdmin)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body?.name).isEqualTo("Misha")
        assertThat(response.body?.surname).isEqualTo("Epstein")
        assertThat(response.body?.email).isEqualTo("misha_epstein@gmail.com")
    }

    @Test
    fun `should delete admin`() {
        val admin = Admin(
            id = 0,
            name = "Dima",
            surname = "Anchukov",
            email = "dva01082009@gmail.com",
            password = "1234"
        )

        val createdAdmin = adminController.createAdmin(admin).body!!

        val response = adminController.deleteAdmin(createdAdmin.id)

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isEqualTo("Admin deleted")
        assertThat(adminController.getAllAdmins()).isEmpty()
    }
}