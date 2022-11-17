package com.serverapp.movieservice.controllers;

import com.serverapp.movieservice.models.Movie;
import com.serverapp.movieservice.repositories.PosterMovie;
import com.serverapp.movieservice.repositories.WideMovie;
import com.serverapp.movieservice.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createMovie(@RequestParam("file") MultipartFile file,
                             @RequestParam("wide") MultipartFile[] wide_images,
                             @RequestParam("title") MultipartFile[] title_images,
                             @RequestParam("poster") MultipartFile[] poster_images,
                             @RequestParam("focus") MultipartFile[] focus_images) throws IOException {
        return movieService.createMovie(file, wide_images, title_images, poster_images, focus_images);
    }

    @GetMapping("/wide")
    @ResponseStatus(HttpStatus.OK)
    public Page<WideMovie> getWide(@RequestParam("offset") int offset, @RequestParam("size") int size) {
        return movieService.getWide(offset, size);
    }

    @GetMapping("/poster")
    @ResponseStatus(HttpStatus.OK)
    public Page<PosterMovie> getPoster(@RequestParam("offset") int offset, @RequestParam("size") int size) {
        return movieService.getPoster(offset, size);
    }


    @RequestMapping(value = "/title/{id}", method = RequestMethod.PATCH)
    public Movie updateTitleMovie(@PathVariable Long id,
                             @RequestParam("title") MultipartFile movie_title) throws IOException{
        return  movieService.updateTitle(id, movie_title);
    }

}
