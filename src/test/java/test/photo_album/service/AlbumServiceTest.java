package test.photo_album.service;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import test.photo_album.domain.entity.Album;
import test.photo_album.dto.AlbumDto;
import test.photo_album.repository.AlbumRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class AlbumServiceTest {

    @Autowired AlbumService albumService;
    @Autowired AlbumRepository albumRepository;
    @Autowired EntityManager em;

    @Test
    void 앨범생성(){
        AlbumDto album = albumService.createAlbum("나의 앨범");
        Album findAlbum = albumRepository.findById(album.getAlbumId()).get();
        assertThat(findAlbum.getName()).isEqualTo(album.getAlbumName());
    }

    @Test
    void 앨범삭제(){
        AlbumDto album = albumService.createAlbum("나의 앨범");
        albumService.deleteAlbum(album.getAlbumId());
        em.flush();
        em.clear();
        Album findAlbum = albumRepository.findById(album.getAlbumId()).orElse(null);
        assertThat(findAlbum).isNull();
    }

    @Test
    void 앨범명_변경(){
        AlbumDto album = albumService.createAlbum("나의 앨범");
        albumService.putAlbum(album.getAlbumId(),"나의 앨범 아님");
        em.flush();
        em.clear();
        Album changeAlbum = albumRepository.findById(album.getAlbumId()).get();
        assertThat(changeAlbum.getName()).isEqualTo("나의 앨범 아님");
    }

    @Test
    void 앨범조회(){
        AlbumDto album = albumService.createAlbum("나의 앨범");
        em.flush();
        em.clear();
        AlbumDto findAlbum = albumService.getAlbum(album.getAlbumId());
        assertThat(album.getAlbumId()).isEqualTo(findAlbum.getAlbumId());
        assertThat(album.getAlbumName()).isEqualTo(findAlbum.getAlbumName());
    }

    @Test
    void 앨범검색(){
        AlbumDto album = albumService.createAlbum("나의 앨범");
        AlbumDto album2 = albumService.createAlbum("나의 앨범2");
        em.flush();
        em.clear();
        LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 5, 4, 3, 2);
        List<AlbumDto> albums = albumService.getAlbums(localDateTime, "앨범");
        log.info("size = {}",albums.size());
        for (AlbumDto albumDto : albums) {
            log.info("albumDto : {}",albumDto);
        }

    }
}