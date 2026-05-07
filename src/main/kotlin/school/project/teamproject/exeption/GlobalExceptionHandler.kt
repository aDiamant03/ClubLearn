package school.project.teamproject.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleNotFound(ex: ResourceNotFoundException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            status = 404,
            error = "NOT_FOUND",
            message = ex.message ?: "Resource not found"
        )

        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequest(ex: BadRequestException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            status = 400,
            error = "BAD_REQUEST",
            message = ex.message ?: "Bad request"
        )

        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            status = 500,
            error = "INTERNAL_SERVER_ERROR",
            message = ex.message ?: "Unknown error"
        )

        return ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}