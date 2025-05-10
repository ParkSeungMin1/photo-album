package test.photo_album.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AlbumDto {
    private Long albumId;
    private String albumName;
    private LocalDateTime createdAt;
    private Integer count;
    private List<String> thumbUrl;
}
