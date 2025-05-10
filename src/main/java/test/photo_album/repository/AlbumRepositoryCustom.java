package test.photo_album.repository;

import org.springframework.stereotype.Repository;
import test.photo_album.domain.Album;
import test.photo_album.domain.Photo;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AlbumRepositoryCustom {
    List<Album> findAlbumSearch(LocalDateTime byDate, String byName);
}
