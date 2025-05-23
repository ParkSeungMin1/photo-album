package test.photo_album.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.photo_album.domain.entity.Photo;

public interface PhotoRepository extends JpaRepository<Photo,Long>, PhotoRepositoryCustom {
    Photo findByIdAndAlbum_id(Long photoId, Long albumId);
    Integer countByAlbum_id(Long albumId);
}
