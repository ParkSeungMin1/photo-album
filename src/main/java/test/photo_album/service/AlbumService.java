package test.photo_album.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.photo_album.domain.Album;
import test.photo_album.domain.Photo;
import test.photo_album.dto.AlbumDto;
import test.photo_album.dto.PhotoDto;
import test.photo_album.repository.AlbumRepository;
import test.photo_album.repository.PhotoRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final PhotoService photoService;

    public List<AlbumDto> getAlbums(LocalDateTime byDate, String byName){
        List<Album> searchAlbums = albumRepository.findAlbumSearch(byDate, byName);
        List<AlbumDto> searchAlbumsDto = new ArrayList<>();
        for (Album search : searchAlbums) {
            searchAlbumsDto.add(getSearchAlbumDto(search));
        }
        return searchAlbumsDto;
    }

    public AlbumDto getAlbum(Long albumId){
        Album findAlbum = albumRepository.findById(albumId).get();
        //int count = findAlbum.getPhotos().size();
        return getAlbumDto(findAlbum,0);
    }

    public AlbumDto createAlbum(String albumName){
        Album createAlbum = albumRepository.save(new Album(albumName));
        //int count = createAlbum.getPhotos().size();

        return getAlbumDto(createAlbum, 0);
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
                .count(null)
                .build();
    }

    private static AlbumDto getSearchAlbumDto(Album album) {
        List<String> thumbUrl = new ArrayList<>();
        //for (Photo photo : album.getPhotos()) {
       //     thumbUrl.add(photo.getThumbUrl());
        //}
        return AlbumDto.builder()
                .albumId(album.getId())
                .albumName(album.getName())
                .count(0)
                .createdAt(album.getCreatedAt())
                .thumbUrl(thumbUrl)
                .build();
    }
}
