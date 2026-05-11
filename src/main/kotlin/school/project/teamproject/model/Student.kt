package school.project.teamproject.model

import jakarta.persistence.*

@Entity
@Table(name = "students")
class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", sequenceName = "student_id_seq", allocationSize = 1)
    var id: Long? = null,

    var name: String = "",
    var surname: String = "",
    var email: String = "",
    var password: String = "",
    var grade: Int? = null
)