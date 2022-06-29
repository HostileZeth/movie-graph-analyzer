package com.mitsudoku.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitsudoku.client.KinopoiskApiClient;
import com.mitsudoku.model.MovieInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieInfoService {

    private final KinopoiskApiClient kinopoiskApiClient;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedDelay = 60*60*1000)
    public void getMovieByName() throws JsonProcessingException {
        String greenMile = kinopoiskApiClient.findByName("Зелёная миля", "1");
        log.info(greenMile);

        MovieInfoDto movieInfoDto = objectMapper.readValue(greenMile, MovieInfoDto.class);
    }
}
