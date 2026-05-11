package school.project.teamproject.filter

import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.UUID
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.FilterChain

import jakarta.servlet.http.HttpServletResponse
@Component
class MdcFilter : OncePerRequestFilter() {

    companion object {
        const val REQUEST_ID_HEADER = "X-Request-ID"
        const val MDC_REQUEST_ID_KEY = "requestId"
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val requestId = request.getHeader(REQUEST_ID_HEADER) ?: UUID.randomUUID().toString()
            MDC.put(MDC_REQUEST_ID_KEY, requestId)

            response.setHeader(REQUEST_ID_HEADER, requestId)
            filterChain.doFilter(request, response)
        } finally {
            MDC.clear()
        }
    }
}