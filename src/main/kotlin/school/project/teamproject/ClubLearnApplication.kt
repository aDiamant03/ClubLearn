package school.project.teamproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import school.project.teamproject.config.AppProperties

@SpringBootApplication
@EnableConfigurationProperties(AppProperties::class)
class ClubLearnApplication

fun main(args: Array<String>) {
    runApplication<ClubLearnApplication>(*args)
}