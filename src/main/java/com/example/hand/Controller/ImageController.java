package com.example.hand.Controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("api/images")
public class ImageController {

    private final Path imageDir = Paths.get(System.getProperty("user.dir"), "images");

    @GetMapping("/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            Path file = imageDir.resolve(filename).normalize();
            if (!file.startsWith(imageDir) || !file.toFile().exists()) {
                return ResponseEntity.notFound().build();
            }
            Resource resource = new UrlResource(file.toUri());
            String contentType = filename.toLowerCase().endsWith(".png")
                    ? MediaType.IMAGE_PNG_VALUE
                    : MediaType.IMAGE_JPEG_VALUE;
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
