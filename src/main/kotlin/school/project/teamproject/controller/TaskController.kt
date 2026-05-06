package school.project.teamproject.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.project.teamproject.model.Task
import school.project.teamproject.service.TaskService

@RestController
@RequestMapping("/api/tasks")
class TaskController(private val taskService: TaskService) {

    @PostMapping
    fun createTask(@RequestBody task: Task): ResponseEntity<Task> {
        val createdTask = taskService.create(task)
        return ResponseEntity(createdTask, HttpStatus.CREATED)
    }

    @GetMapping
    fun getAllTasks(): ResponseEntity<List<Task>> {
        val tasks = taskService.getAll()
        return ResponseEntity(tasks, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<Task> {
        val task = taskService.getById(id)
        return if (task != null) {
            ResponseEntity(task, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: Long, @RequestBody task: Task): ResponseEntity<Task> {
        val updatedTask = taskService.update(id, task)
        return if (updatedTask != null) {
            ResponseEntity(updatedTask, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: Long): ResponseEntity<Void> {
        return if (taskService.delete(id)) {
            ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}