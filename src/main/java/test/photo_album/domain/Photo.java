package test.photo_album.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Photo {

    @Id
    @GeneratedValue
    @Column(name = "photo_id")
    private Long id;

    private String fileName;
    private String thumbUrl;
    private String originalUrl;
    private Long fileSize;
    private LocalDateTime uploadedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    public void setAlbum(Album album) {
        this.album = album;
    }

}
