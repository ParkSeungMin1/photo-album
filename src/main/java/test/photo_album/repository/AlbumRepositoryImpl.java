package test.photo_album.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import test.photo_album.domain.entity.Album;
import test.photo_album.domain.entity.QAlbum;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class AlbumRepositoryImpl implements AlbumRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final test.photo_album.domain.entity.QAlbum album = QAlbum.album;

    public AlbumRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Album> findAlbumSearch(LocalDateTime byDate, String byName) {
        return queryFactory
                .selectFrom(album)
                .where(byDateGoe(byDate), byNameContains(byName))
                .fetch();
    }

    private BooleanExpression byNameContains(String byName) {
        return byName != null ? album.name.contains(byName) : null;
    }

    private BooleanExpression byDateGoe(LocalDateTime byDate) {
        return byDate != null ? album.createdAt.after(byDate) : null;
    }
}
