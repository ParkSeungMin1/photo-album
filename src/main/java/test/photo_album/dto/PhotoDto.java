package test.photo_album.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhotoDto {

    private Long photoId;
    private String fileName;
    private String thumbUrl;
    private LocalDateTime uploadedAt;
}
