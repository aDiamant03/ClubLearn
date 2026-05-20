package school.project.teamproject.service

import org.springframework.stereotype.Service
import school.project.teamproject.model.Admin
import java.util.concurrent.atomic.AtomicLong

@Service
class AdminService {
    private val admins = mutableListOf<Admin>()
    private val idGenerator = AtomicLong(1)

    fun create(admin: Admin): Admin {
        val newAdmin = admin.copy(id = idGenerator.getAndIncrement())
        admins.add(newAdmin)
        return newAdmin
    }

    fun getAll(): List<Admin> {
        return admins.toList()
    }

    fun getById(id: Long): Admin? {
        return admins.find { it.id == id }
    }

    fun delete(id: Long): Boolean {
        return admins.removeIf { it.id == id }
    }

    fun update(id: Long, admin: Admin): Admin? {
        val index = admins.indexOfFirst { it.id == id }

        if (index == -1) {
            return null
        }

        val updatedAdmin = admin.copy(id = id)
        admins[index] = updatedAdmin
        return updatedAdmin
    }
}
