package test.photo_album.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.photo_album.domain.Album;

public interface AlbumRepository extends JpaRepository<Album,Long> {

}
