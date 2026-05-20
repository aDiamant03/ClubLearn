package school.project.teamproject.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "app")
data class AppProperties(
    var maxTasks: Int = 10,
    var bannedNames: List<String> = emptyList(),
    var security: Security = Security()
) {
    data class Security(
        var username: String = "admin",
        var password: String = "admin"
    )
}