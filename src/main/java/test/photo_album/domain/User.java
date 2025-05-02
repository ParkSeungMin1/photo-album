package test.photo_album.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "app_users")
@Getter
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String password;
    private String email;
    @CreatedDate
    private LocalDateTime createdAt;
    private LocalDateTime loginAt;

    public User() {
    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
