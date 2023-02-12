package by.zmeyka.LessonProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="user_name")
    private String userName;
    @Column(name="user_lastname")
    private String userLastName;
    @Column(name="user_email")
    private String email;
}
