package test.photo_album.dto;

import lombok.Data;

import java.util.List;

@Data
public class MoveRequest {
    Long fromAlbumId;
    Long toAlbumId;
    List<Long> photoIds;
}
