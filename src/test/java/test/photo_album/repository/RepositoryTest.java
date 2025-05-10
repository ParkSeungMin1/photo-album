package test.photo_album.repository;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import test.photo_album.domain.entity.Album;
import test.photo_album.domain.entity.User;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
public class RepositoryTest {

    @Autowired
    EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    AlbumRepository albumRepository;

    @Test
    void 유저_CRUD() throws InterruptedException {
        User user1 = new User("hello", "131231223123", "pp210jw@gmail.com");
        userRepository.save(user1);
        log.info("user1 : {}",user1);
        User findUser = userRepository.findById(user1.getId()).orElse(new User());
        assertThat(user1.getId()).isEqualTo(findUser.getId());
        em.flush();
        em.clear();

        userRepository.delete(user1);
        List<User> findName = userRepository.findByName("hello");
        assertThat(findName.size()).isEqualTo(0);
    }

    /**
     * 앨범 crud
     */
    @Test
    void 앨범_CRUD(){
        User user1 = new User("hello", "131231223123", "pp210jw@gmail.com");
        userRepository.save(user1);

        Album album = new Album("hello");
        album.setUser(user1);
        albumRepository.save(album);
        Album findAlbum = albumRepository.findById(album.getId()).get();
        log.info("album = {}",album);
        assertThat(album.getId()).isEqualTo(findAlbum.getId());
        em.flush();
        em.clear();
        log.info("초기화");
        Album deleteAlbum = albumRepository.findById(album.getId()).get();
        albumRepository.delete(deleteAlbum);
        em.flush();
        em.clear();
        Album deletedFindAlbum = albumRepository.findById(album.getId()).orElse(null);
        assertThat(deletedFindAlbum).isNull();
    }

}
