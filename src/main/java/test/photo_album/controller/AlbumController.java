package test.photo_album.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import test.photo_album.dto.AlbumDto;
import test.photo_album.service.AlbumService;

import java.util.Map;

@RestController
@RequestMapping("/albums")
@RequiredArgsConstructor
@Slf4j
public class AlbumController {

    private final AlbumService albumService;

    @PostMapping
    public AlbumDto create(@RequestBody Map<String,String> name){
        String albumName = name.get("albumName");
        return albumService.createAlbum(albumName);
    }

    @DeleteMapping("/{albumId}")
    public void delete(@PathVariable Long albumId){
        albumService.deleteAlbum(albumId);
    }

    @PutMapping("/{albumId}")
    public AlbumDto put(@PathVariable Long albumId, @RequestBody Map<String, String> name){
        String albumName = name.get("albumName");
        return albumService.putAlbum(albumId,albumName);
    }

    @GetMapping("/{albumId}")
    public AlbumDto getAlbum(@PathVariable Long albumId){
        return albumService.getAlbum(albumId);
    }

}
