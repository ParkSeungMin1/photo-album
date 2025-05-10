package test.photo_album.service;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import test.photo_album.domain.entity.Album;
import test.photo_album.repository.AlbumRepository;
import test.photo_album.repository.PhotoRepository;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Slf4j
@SpringBootTest
class PhotoServiceTest {

    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    PhotoService photoService;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    EntityManager em;

    @Test
    void 사진목록_불러오기(){
        Album album1 = albumRepository.save(new Album("내 앨범"));
        Album album2 = albumRepository.save(new Album("내 앨범2"));
        em.flush();
        em.clear();
    }

    @Test
    void 사진_업로드(){
        Album album1 = albumRepository.save(new Album("내 앨범"));
        em.flush();
        em.clear();
        List<String> fileNames = new ArrayList<>();
        fileNames.add("hello0");
        fileNames.add("hello1");
        //List<PhotoDto> photoDtos = photoService.uploadPhotos(album1.getId(), fileNames);
        //assertThat(photoDtos.get(0).getFileName()).isEqualTo(fileNames.get(0));
        //assertThat(photoDtos.get(1).getFileName()).isEqualTo(fileNames.get(1));
    }

}