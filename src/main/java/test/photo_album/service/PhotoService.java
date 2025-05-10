package test.photo_album.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import test.photo_album.domain.Album;
import test.photo_album.domain.Photo;
import test.photo_album.domain.QAlbum;
import test.photo_album.dto.PhotoDto;
import test.photo_album.repository.AlbumRepository;
import test.photo_album.repository.PhotoRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static test.photo_album.domain.QAlbum.album;

@Service
@RequiredArgsConstructor
@Transactional
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final AlbumRepository albumRepository;

    /**
     * 사진 목록 불러오기
     */
    public List<PhotoDto> getPhotos(Long albumId, LocalDateTime byDate, String byName){
        List<Photo> photos = photoRepository.searchPhotos(albumId,byDate,byName);
        return getPhotosDto(photos);
    }

    /**
     * 사진 업로드
     * 쿼리 두번 나가야하는지? save 쿼리, albumId로 album 조회 쿼리
     */
    public List<PhotoDto> uploadPhotos(Long albumId,List<MultipartFile> files){
        Album album = albumRepository.findById(albumId).get();
        List<PhotoDto> photosDto = new ArrayList<>();
        List<Photo> photos = new ArrayList<>();
        for (MultipartFile file : files) {
            photos.add(new Photo(file.getOriginalFilename(), "https://thumb/"+file.getOriginalFilename(), "https://thumb/"+file.getName(),file.getSize(),album));
        }
        photoRepository.saveAll(photos);
        photosDto = getPhotosDto(photos);
        return photosDto;
    }

    private List<PhotoDto> getPhotosDto(List<Photo> photos) {
        List<PhotoDto> photosDto = new ArrayList<>();
        for (Photo photo : photos) {
            photosDto.add(PhotoDto.builder()
                    .photoId(photo.getId())
                    .fileName(photo.getFileName())
                    .thumbUrl(photo.getThumbUrl())
                    .uploadedAt(photo.getUploadedAt())
                    .build()
            );
        }
        return photosDto;
    }
}
