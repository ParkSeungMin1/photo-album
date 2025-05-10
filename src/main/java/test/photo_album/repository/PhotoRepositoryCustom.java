package test.photo_album.repository;

import org.springframework.stereotype.Repository;
import test.photo_album.domain.Photo;

import java.time.LocalDateTime;
import java.util.List;

public interface PhotoRepositoryCustom {
    List<Photo> searchPhotos(Long albumId,LocalDateTime byDate, String byName);
}
