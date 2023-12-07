package io.arrogantprogrammer.grpc;

import io.arrogantprogrammer.proto.Empty;
import io.arrogantprogrammer.proto.GRPCFilmService;
import io.quarkus.grpc.GrpcClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import io.arrogantprogrammer.domain.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Path("/grpc-client")
public class GRPCClientResource {
    static final Logger LOGGER = LoggerFactory.getLogger(GRPCClientResource.class);

    @GrpcClient("grpc-film-client")
    GRPCFilmService grpcFilmServiceClient;

    @GET
    public List<Film> allFilms() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        return grpcFilmServiceClient.allFilms(Empty.newBuilder().build())
                .onItem().transform(allFilmsProtos -> {
                    List<Film> films = new ArrayList<>();
                    allFilmsProtos.getFilmsList().forEach(filmProto -> {
                        Film film = new Film();
                        film.setTitle(filmProto.getTitle());
                        film.setEpisodeID(filmProto.getEpisodeId());
                        film.setDirector(filmProto.getDirector());
                        film.setReleaseDate(LocalDate.parse(filmProto.getReleaseDate(), formatter));
                        films.add(film);
                    });
                    return films;
                }).await().indefinitely();
    }
}
