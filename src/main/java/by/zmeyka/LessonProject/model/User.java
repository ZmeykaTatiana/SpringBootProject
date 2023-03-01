package by.zmeyka.LessonProject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

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
    @NotEmpty(message="name can't be empty")

    private String userName;
    @Column(name="user_lastname")

    private String userLastName;
    @Column(name="user_email")

    private String email;


    @ManyToOne
    @JoinColumn(name="office_id")
    private Office office;

    public User(String userName, String userLastName, String email) {
        this.userName = userName;
        this.userLastName = userLastName;
        this.email = email;
    }
}
