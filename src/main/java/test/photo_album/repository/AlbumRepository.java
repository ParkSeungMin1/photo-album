package test.photo_album.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.photo_album.domain.Album;

import java.time.LocalDateTime;
import java.util.List;

public interface AlbumRepository extends JpaRepository<Album,Long>,AlbumRepositoryCustom {

}
