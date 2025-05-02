package test.photo_album.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
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

    @PrePersist
    protected void upload(){
        uploadedAt = LocalDateTime.now();
    }
}
