package test.photo_album.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Photo {

    @Id
    @GeneratedValue
    @Column(name = "photo_id")
    private Long id;

    private String fileName;
    private String thumbUrl;
    private String originalUrl;
    private Long fileSize;
    @CreatedDate
    private LocalDateTime uploadedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Photo(String fileName, String thumbUrl, String originalUrl, Long fileSize) {
        this.fileName = fileName;
        this.thumbUrl = thumbUrl;
        this.originalUrl = originalUrl;
        this.fileSize = fileSize;
    }
}
