package com.serverapp.trailerservice.services;

import com.serverapp.trailerservice.Repositories.TrailerManifestRepository;
import com.serverapp.trailerservice.Repositories.TrailerSegmentsRepository;
import com.serverapp.trailerservice.models.TrailerSegment;


import com.serverapp.trailerservice.models.TrailerManifest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    private TrailerManifestRepository trailerManifestRepository;
    @Autowired
    private TrailerSegmentsRepository trailerSegmentsRepository;

    public String uploadVideo(MultipartFile[] fileSegment, String name) throws IOException {
        for (int i = 0; i < fileSegment.length; i ++) {
           if(fileSegment[i].getContentType().equals("application/dash+xml")) {
               TrailerManifest manifest = new TrailerManifest();
               manifest.setVideo_name(name);
               manifest.setManifest_name(fileSegment[i].getOriginalFilename());
               manifest.setManifest_type(fileSegment[i].getContentType());
               manifest.setManifest_data(fileSegment[i].getBytes());
               trailerManifestRepository.save(manifest);

           } else {
               TrailerSegment trailer = new TrailerSegment();
               trailer.setSegment_name(fileSegment[i].getOriginalFilename());
               trailer.setSegment_size(fileSegment[i].getSize());
               trailer.setSegment_type(fileSegment[i].getContentType());
               trailer.setSegment_data(fileSegment[i].getBytes());
               trailerSegmentsRepository.save(trailer);
           }
        }
        return "Upload Completed";
    }

    public TrailerSegment getVideoSegment(String name) {
        return trailerSegmentsRepository.findSegment(name);
    }

    public TrailerManifest getManifest(String name) {
        return trailerManifestRepository.findManifest(name);
    }

    public Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}
