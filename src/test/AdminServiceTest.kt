package school.project.teamproject

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import school.project.teamproject.model.Admin
import school.project.teamproject.service.AdminService

class AdminServiceTest {

    private lateinit var adminService: AdminService

    @BeforeEach
    fun setUp() {
        adminService = AdminService()
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

        val createdAdmin = adminService.create(admin)

        assertThat(createdAdmin.id).isEqualTo(1)
        assertThat(createdAdmin.name).isEqualTo("Dima")
        assertThat(createdAdmin.surname).isEqualTo("Anchukov")
        assertThat(createdAdmin.email).isEqualTo("dva01082009@gmail.com")
        assertThat(adminService.getAll()).hasSize(1)
    }

    @Test
    fun `should return all admins`() {
        val admin1 = Admin(
            id = 0,
            name = "Dima",
            surname = "Anchukov",
            email = "dva01082009@gmail.com",
            password = "1234"
        )

        val admin2 = Admin(
            id = 0,
            name = "Misha",
            surname = "Epstein",
            email = "misha_epstein@gmail.com",
            password = "5678"
        )

        adminService.create(admin1)
        adminService.create(admin2)

        val admins = adminService.getAll()

        assertThat(admins).hasSize(2)
    }

    @Test
    fun `should find admin by id`() {
        val admin = Admin(
            id = 0,
            name = "Dima",
            surname = "Anchukov",
            email = "dva01082009@gmail.com",
            password = "1234"
        )

        val createdAdmin = adminService.create(admin)
        val foundAdmin = adminService.getById(createdAdmin.id)

        assertThat(foundAdmin).isEqualTo(createdAdmin)
    }

    @Test
    fun `should return null if admin not found`() {
        val foundAdmin = adminService.getById(100)

        assertThat(foundAdmin).isNull()
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

        val createdAdmin = adminService.create(admin)

        val newAdmin = Admin(
            id = 0,
            name = "Misha",
            surname = "Epstein",
            email = "misha_epstein@gmail.com",
            password = "9999"
        )

        val updatedAdmin = adminService.update(createdAdmin.id, newAdmin)

        assertThat(updatedAdmin).isNotNull
        assertThat(updatedAdmin?.id).isEqualTo(1)
        assertThat(updatedAdmin?.name).isEqualTo("Misha")
        assertThat(updatedAdmin?.surname).isEqualTo("Epstein")
        assertThat(updatedAdmin?.email).isEqualTo("misha_epstein")
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

        val createdAdmin = adminService.create(admin)

        val result = adminService.delete(createdAdmin.id)

        assertThat(result).isTrue()
        assertThat(adminService.getAll()).isEmpty()
    }
}