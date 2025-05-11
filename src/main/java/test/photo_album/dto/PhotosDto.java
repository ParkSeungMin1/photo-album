package test.photo_album.dto;

import lombok.Data;

import java.util.List;

@Data
public class PhotosDto {
    List<PhotoDto> photos;
    public PhotosDto(List<PhotoDto> photos) {
        this.photos = photos;
    }
}
