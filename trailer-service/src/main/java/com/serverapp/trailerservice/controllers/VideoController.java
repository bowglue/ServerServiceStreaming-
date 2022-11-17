package com.serverapp.trailerservice.controllers;

import com.serverapp.trailerservice.models.TrailerSegment;
import com.serverapp.trailerservice.models.TrailerManifest;
import com.serverapp.trailerservice.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.EncoderException;


import java.io.IOException;

@RestController
@RequestMapping("/api/v1/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @PostMapping()
    public ResponseEntity<?> uploadVideo( @RequestParam("fileSegment") MultipartFile[] fileSegment, @RequestParam("name") String name) throws IOException, EncoderException {
        return new ResponseEntity<>(videoService.uploadVideo(fileSegment, name), HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<ByteArrayResource> streamVideo(@PathVariable String name) throws IOException {
        if(!videoService.getExtensionByStringHandling(name).isPresent()) {
            TrailerManifest manifest = videoService.getManifest(name);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(manifest.getManifest_type()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + manifest.getManifest_name() + "\"")
                    .body(new ByteArrayResource(manifest.getManifest_data()));
        }

        TrailerSegment video = videoService.getVideoSegment(name);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(video.getSegment_type()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + video.getSegment_name() + "\"")
                .body(new ByteArrayResource(video.getSegment_data()));

    }
}
