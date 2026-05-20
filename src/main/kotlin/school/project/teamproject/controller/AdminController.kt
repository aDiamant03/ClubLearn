package school.project.teamproject.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import school.project.teamproject.model.Admin
import school.project.teamproject.service.AdminService

@RestController
@RequestMapping("/admins")
class AdminController(
    private val adminService: AdminService
) {

    @PostMapping
    fun createAdmin(@RequestBody admin: Admin): ResponseEntity<Admin> {
        val newAdmin = adminService.create(admin)
        return ResponseEntity.status(HttpStatus.CREATED).body(newAdmin)
    }

    @GetMapping
    fun getAllAdmins(): List<Admin> {
        return adminService.getAll()
    }

    @GetMapping("/{id}")
    fun getAdminById(@PathVariable id: Long): ResponseEntity<Admin> {
        val admin = adminService.getById(id)

        if (admin == null) {
            return ResponseEntity.notFound().build()
        }

        return ResponseEntity.ok(admin)
    }

    @PutMapping("/{id}")
    fun updateAdmin(
        @PathVariable id: Long,
        @RequestBody admin: Admin
    ): ResponseEntity<Admin> {
        val updatedAdmin = adminService.update(id, admin)

        if (updatedAdmin == null) {
            return ResponseEntity.notFound().build()
        }

        return ResponseEntity.ok(updatedAdmin)
    }

    @DeleteMapping("/{id}")
    fun deleteAdmin(@PathVariable id: Long): ResponseEntity<String> {
        val deleted = adminService.delete(id)

        if (!deleted) {
            return ResponseEntity.notFound().build()
        }

        return ResponseEntity.ok("Admin deleted")
    }
}