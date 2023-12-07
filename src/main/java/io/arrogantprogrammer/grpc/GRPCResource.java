package io.arrogantprogrammer.grpc;

import io.arrogantprogrammer.domain.GalaxyService;
import io.arrogantprogrammer.proto.*;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.stream.Collectors.toList;

@GrpcService
public class GRPCResource implements GRPCFilmService {

    static final Logger LOGGER = LoggerFactory.getLogger(GRPCResource.class);

    @Inject
    GalaxyService galaxyService;


    @Override
    public Uni<AllFilmsProtos> allFilms(Empty request) {
        List<FilmProto> allFilms = galaxyService.getAllFilms().stream().map(film -> {
            LOGGER.debug("mapping film {} to FilmProto", film.getTitle());
            return FilmProto.newBuilder()
                    .setTitle(film.getTitle())
                    .setEpisodeId(film.getEpisodeID())
                    .setDirector(film.getDirector())
                    .setReleaseDate(film.getReleaseDate().toString()).build();
        }).collect(toList());

        return Uni.createFrom().item(AllFilmsProtos.newBuilder().addAllFilms(allFilms).build());
    }

    @Override
    public Uni<AllHeroProtos> allHeroes(Empty request) {
        List<HeroProto> allHeroes = galaxyService.getHeroes().stream().map(hero -> {
            LOGGER.debug("mapping hero {} to HeroProto", hero);
            return HeroProto.newBuilder()
                    .setDarkSide(hero.getDarkSide())
                    .setName(hero.getName())
                    .setHeight(hero.getHeight().intValue())
                    .setMass(hero.getMass())
                    .setSurname(hero.getSurname())
                    .addAllEpisodeIds(hero.getEpisodeIds()).build();

        }).collect(toList());
        return Uni.createFrom().item(AllHeroProtos.newBuilder().addAllHeroes(allHeroes).build());
    }
}
