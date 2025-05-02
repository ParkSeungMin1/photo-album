package test.photo_album.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.photo_album.domain.Photo;
import test.photo_album.dto.PhotoDto;
import test.photo_album.repository.PhotoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PhotoService {

    private final PhotoRepository photoRepository;

    public List<PhotoDto> getPhoto(Long albumId){
        List<Photo> photoList = photoRepository.findByAlbumId(albumId);
        List<PhotoDto> photoDto = null;
        return photoDto;
    }

    public Integer getPhotoSize(Long albumId){
        List<Photo> photoList = photoRepository.findByAlbumId(albumId);
        return photoList.size();
    }
}
