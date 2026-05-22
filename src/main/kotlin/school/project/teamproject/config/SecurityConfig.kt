package school.project.teamproject.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    @Bean
    fun userDetailsService(passwordEncoder: PasswordEncoder): UserDetailsService {
        val admin = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("admin123"))
            .roles("ADMIN")
            .build()

        val teacher = User.builder()
            .username("teacher")
            .password(passwordEncoder.encode("teacher123"))
            .roles("TEACHER")
            .build()

        val student = User.builder()
            .username("student")
            .password(passwordEncoder.encode("student123"))
            .roles("STUDENT")
            .build()

        return InMemoryUserDetailsManager(admin, teacher, student)
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/", "/index.html", "/favicon.ico").permitAll()
                    .requestMatchers("/actuator/**").permitAll()
                    .requestMatchers("/api/tasks/**").hasAnyRole("TEACHER", "STUDENT", "ADMIN")
                    .requestMatchers("/api/answers/**").hasAnyRole("TEACHER", "STUDENT", "ADMIN")
                    .anyRequest().permitAll()
            }
            .httpBasic(Customizer.withDefaults())
            .build()
    }
}