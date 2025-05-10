package test.photo_album.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import test.photo_album.dto.PhotoDto;
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
    public Map<String,List<PhotoDto>> getPhotos(@PathVariable Long albumId, @RequestParam(value = "sort", required = false)LocalDateTime byDate, @RequestParam(value = "keyword",required = false) String byName){
        List<PhotoDto> photo = photoService.getPhotos(albumId,byDate,byName);
        Map<String,List<PhotoDto>> response = new HashMap<>();
        response.put("photos",photo);
        return response;

    }

    @PostMapping("/{albumId}/photos")
    public Map<String,List<PhotoDto>> getPhotos(@PathVariable Long albumId, @RequestParam("images")List<MultipartFile> files){
        List<PhotoDto> photo = photoService.uploadPhotos(albumId, files);
        Map<String,List<PhotoDto>> response = new HashMap<>();
        response.put("photos",photo);
        return response;
    }
}
