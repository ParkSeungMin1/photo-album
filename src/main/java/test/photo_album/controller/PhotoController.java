package test.photo_album.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import test.photo_album.dto.PhotoDto;
import test.photo_album.dto.PhotosDto;
import test.photo_album.service.PhotoService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/albums")
@RequiredArgsConstructor
@Slf4j
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping("/{albumId}/photos")
    public PhotosDto getPhotos(@PathVariable Long albumId, @RequestParam(value = "sort", required = false)LocalDateTime byDate, @RequestParam(value = "keyword",required = false) String byName){
        return new PhotosDto(photoService.getPhotos(albumId,byDate,byName));
    }

    @PostMapping("/{albumId}/photos")
    public PhotosDto uploadPhotos(@PathVariable Long albumId, @RequestParam("images")List<MultipartFile> files){
        return new PhotosDto(photoService.uploadPhotos(albumId, files));
    }

    @GetMapping("/{albumId}/photos/{photoId}")
    public PhotoDto getPhoto(@PathVariable Long albumId, @PathVariable Long photoId){
        return photoService.getPhoto(albumId, photoId);
    }

    @DeleteMapping("/{albumId}/photos")
    public PhotosDto deletePhotos(@PathVariable Long albumId,@RequestBody Map<String,List<Long>> requestBody){
        List<Long> photoIds = requestBody.get("photoIds");
        return new PhotosDto(photoService.deletePhotos(albumId, photoIds));
    }

//    @PutMapping("/albums/{albumId}/photos/move")
//    public Map<String, List<PhotoDto>>
}
