package test.photo_album.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import test.photo_album.domain.entity.Album;
import test.photo_album.domain.entity.Photo;
import test.photo_album.dto.PhotoDto;
import test.photo_album.repository.AlbumRepository;
import test.photo_album.repository.PhotoRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final AlbumRepository albumRepository;
    private static final String UPLOAD_PATH = "C:/Users/user/Desktop/photo-album/src/main/resources/images/";
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
            String ext = getExt(file);
            String storedFilename = UUID.randomUUID().toString()+'.'+ext;
            try {
                Path filePath = Paths.get(UPLOAD_PATH,storedFilename);
                Files.createDirectories(filePath.getParent());
                file.transferTo(filePath.toFile());
                photos.add(new Photo(file.getOriginalFilename(), filePath.toString(), storedFilename,file.getSize(),album));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        photoRepository.saveAll(photos);
        photosDto = getPhotosDto(photos);
        return photosDto;
    }

    private String getExt(MultipartFile multipartFile){
        String originalFilename = multipartFile.getOriginalFilename();
        String ext = "";
        int pos = originalFilename.lastIndexOf(".");
        if (pos != -1) {
            ext = originalFilename.substring(pos + 1);
        }
        return ext;
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
