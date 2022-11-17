package com.serverapp.movieservice.services;

import com.serverapp.movieservice.models.Movie;
import com.serverapp.movieservice.repositories.MovieRepository;
import com.serverapp.movieservice.repositories.PosterMovie;
import com.serverapp.movieservice.repositories.WideMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public String createMovie(MultipartFile file, MultipartFile[] wide_images, MultipartFile[] title_images, MultipartFile[] poster_images, MultipartFile[] focus_images) throws IOException {

        //name|wide|title|poster|focus
        try (BufferedReader br =new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\\|");
                Movie movie = new Movie();
                movie.setName(values[0]);
                //Wide_images
                Arrays.stream(wide_images).filter((wide_image) ->
                    values[1].equals(wide_image.getOriginalFilename().split("\\.")[0])
                ).peek((wide_image) -> {
                    try {
                        movie.setWide_image(wide_image.getBytes());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());

                //Title images
                Arrays.stream(title_images).filter((title_image) ->
                        values[2].equals(title_image.getOriginalFilename().split("\\.")[0])
                ).peek((title_image) -> {
                    try {
                        movie.setTitle_image(title_image.getBytes());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());

                //Poster images
                Arrays.stream(poster_images).filter((poster_image) ->
                        values[3].equals(poster_image.getOriginalFilename().split("\\.")[0])
                ).peek((poster_image) -> {
                    try {
                        movie.setPoster_image(poster_image.getBytes());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());


                //Focus images
                Arrays.stream(focus_images).filter((focus_image) ->
                        values[4].equals(focus_image.getOriginalFilename().split("\\.")[0])
                ).peek((focus_image) -> {
                    try {
                        movie.setFocus_image(focus_image.getBytes());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());
                movieRepository.saveAndFlush(movie);
            }
        }
        return "movie Uploaded";
    }

    public Page<WideMovie> getWide(int offset, int size) {
        Pageable paging = new OffsetPageRequest(offset, size);
        return movieRepository.findWide(paging);

    }

    public Page<PosterMovie> getPoster(int offset, int size) {
        Pageable paging = new OffsetPageRequest(offset, size);
        return movieRepository.findPoster(paging);
    }

    public Movie updateTitle(Long id, MultipartFile movie_title) throws IOException {
        Movie movie = movieRepository.getReferenceById(id);
        movie.setTitle_image(movie_title.getBytes());
        return movieRepository.saveAndFlush(movie);
    }
}
