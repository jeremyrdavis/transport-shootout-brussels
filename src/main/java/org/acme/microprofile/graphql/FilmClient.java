package org.acme.microprofile.graphql;

import io.smallrye.graphql.client.typesafe.api.GraphQLClientApi;
import org.acme.domain.Film;

import java.util.List;

@GraphQLClientApi(configKey = "star-wars-typesafe")
public interface FilmClient {

    List<Film> allFilms();

}
