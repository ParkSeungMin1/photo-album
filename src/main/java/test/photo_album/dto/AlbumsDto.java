package test.photo_album.dto;

import lombok.Data;

import java.util.List;

@Data
public class AlbumsDto {
    List<AlbumDto> albums;

    public AlbumsDto(List<AlbumDto> albums) {
        this.albums = albums;
    }
}
