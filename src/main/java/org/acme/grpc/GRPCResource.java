package org.acme.grpc;

import io.arrogantprogrammer.proto.AllFilmsProtos;
import io.arrogantprogrammer.proto.Empty;
import io.arrogantprogrammer.proto.FilmProto;
import io.arrogantprogrammer.proto.GRPCFilmService;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.acme.domain.GalaxyService;
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
}
