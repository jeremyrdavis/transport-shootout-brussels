package org.acme.microprofile.graphql;

import io.smallrye.graphql.client.Response;
import io.smallrye.graphql.client.core.Document;
import io.smallrye.graphql.client.dynamic.api.DynamicGraphQLClient;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.domain.Film;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.smallrye.graphql.client.GraphQLClient;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static io.smallrye.graphql.client.core.Document.document;
import static io.smallrye.graphql.client.core.Field.field;
import static io.smallrye.graphql.client.core.Operation.operation;

@Path("/graphql-client")
public class GraphQLClientResource {

    static final Logger LOGGER = LoggerFactory.getLogger(GraphQLClientResource.class);

    @Inject
    FilmClient typesafeClient;

    @Inject
    @GraphQLClient("star-wars-dynamic")
    DynamicGraphQLClient dynamicClient;

    @GET
    public List<Film> allFilms() throws ExecutionException, InterruptedException {

        //return dynamicClient.allFilms();
        Document query = document(
                operation(
                        field("allFilms",
                                field("director")
                )
        ));
        Response response = dynamicClient.executeSync(query);
        LOGGER.debug("response: {}", response);
        return new ArrayList<>();
    }
}
