package test.photo_album.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Album {

    @Id
    @GeneratedValue
    @Column(name = "album_id")
    private Long id;

    private String name;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    protected void create(){
        createdAt = LocalDateTime.now();
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Album() {
    }

    public Album(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                '}';
    }
}
