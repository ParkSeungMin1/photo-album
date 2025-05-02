package test.photo_album.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.photo_album.domain.Album;
import test.photo_album.dto.AlbumDto;
import test.photo_album.dto.PhotoDto;
import test.photo_album.repository.AlbumRepository;
import test.photo_album.repository.PhotoRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final PhotoService photoService;

    public AlbumDto getAlbum(Long albumId){
        Album findAlbum = albumRepository.findById(albumId).get();
        int count = photoService.getPhotoSize(findAlbum.getId());
        return getAlbumDto(findAlbum,count);
    }

    public AlbumDto createAlbum(String albumName){
        Album createAlbum = albumRepository.save(new Album(albumName));
        int count = photoService.getPhotoSize(createAlbum.getId());

        return getAlbumDto(createAlbum, count);
    }

    public void deleteAlbum(Long albumId){
        albumRepository.deleteById(albumId);
    }

    public AlbumDto putAlbum(Long albumId, String changeAlbumName){
        Album album = albumRepository.findById(albumId).get();
        album.changeAlbumName(changeAlbumName);
        return getPutAlbumDto(album);
    }

    private static AlbumDto getAlbumDto(Album create, int count) {
        return AlbumDto.builder()
                .albumId(create.getId())
                .albumName(create.getName())
                .createdAt(create.getCreatedAt())
                .count(count)
                .build();
    }

    private static AlbumDto getPutAlbumDto(Album album) {
        return AlbumDto.builder()
                .albumId(album.getId())
                .albumName(album.getName())
                .build();
    }
}
