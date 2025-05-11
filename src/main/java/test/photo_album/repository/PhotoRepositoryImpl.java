package test.photo_album.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import test.photo_album.domain.entity.Photo;
import test.photo_album.domain.entity.QPhoto;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PhotoRepositoryImpl implements PhotoRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QPhoto photo = QPhoto.photo;
    private final EntityManager em;

    public PhotoRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Photo> searchPhotos(Long albumId,LocalDateTime byDate, String byName) {
        return queryFactory
                .selectFrom(photo)
                .where(albumIdEq(albumId),byDateAfter(byDate),byNameContains(byName))
                .fetch();
    }

    @Override
    public void deletePhotosByIds(Long albumId,List<Long> photoIds) {
        queryFactory
                .delete(photo)
                .where(photo.id.in(photoIds),albumIdEq(albumId))
                .execute();
        em.flush();
        em.clear();
    }

    @Override
    public void movePhotosByIds(Long fromAlbumId, Long toAlbumId, List<Long> photoIds) {
        queryFactory
                .update(photo)
                .set(photo.album.id,toAlbumId)
                .where(albumIdEq(fromAlbumId), photo.id.in(photoIds))
                .execute();
        em.flush();
        em.clear();
    }

    private BooleanExpression albumIdEq(Long albumId) {
        return photo.album.id.eq(albumId);
    }

    private BooleanExpression byNameContains(String byName) {
        return byName == null ? null : photo.fileName.contains(byName);
    }

    private BooleanExpression byDateAfter(LocalDateTime byDate) {
        return byDate == null ? null : photo.uploadedAt.after(byDate);
    }
}
