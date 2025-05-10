package test.photo_album.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.photo_album.domain.Album;
import test.photo_album.domain.Photo;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo,Long>, PhotoRepositoryCustom {
}
