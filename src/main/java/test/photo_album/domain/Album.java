package test.photo_album.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Album {

    @Id
    @GeneratedValue
    @Column(name = "album_id")
    private Long id;

    private String name;
    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Photo> photos = new ArrayList<>();

    public void setUser(User user) {
        this.user = user;
    }

    public void addPhoto(Photo photo){
        this.photos.add(photo);
        if(photo.getAlbum() != this){
            photo.setAlbum(this);
        }
    }

    public void changeAlbumName(String name){
        this.name = name;
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
