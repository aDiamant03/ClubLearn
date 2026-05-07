package school.project.teamproject.controller

import org.springframework.web.bind.annotation.*
import school.project.teamproject.model.Task
import school.project.teamproject.service.TaskService

@RestController
@RequestMapping("/tasks")
class TaskController(
    private val taskService: TaskService
) {

    @GetMapping
    fun getAllTasks(): List<Task> {
        return taskService.getAllTasks()
    }

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): Task {
        return taskService.getTaskById(id)
    }

    @PostMapping
    fun createTask(@RequestBody task: Task): Task {
        return taskService.createTask(task)
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: Long) {
        taskService.deleteTask(id)
    }
}