package test.photo_album.repository;

import test.photo_album.domain.entity.Photo;

import java.time.LocalDateTime;
import java.util.List;

public interface PhotoRepositoryCustom {
    List<Photo> searchPhotos(Long albumId,LocalDateTime byDate, String byName);
    void deletePhotosByIds(Long albumId,List<Long> photoIds);
    void movePhotosByIds(Long fromAlbumId, Long toAlbumId, List<Long> photoIds);
}
