package org.acme.resteasy;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.acme.domain.Film;
import org.acme.domain.GalaxyService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/rest-client")
public class RESTClientResource {
    static final Logger LOGGER = LoggerFactory.getLogger(RESTClientResource.class);

    @RestClient
    RESTClient restClient;

    @GET
    public List<Film> allFilms() {
        LOGGER.debug("allFilms");
        List<Film> films = restClient.allFilms();
        films.stream().map(Film::getTitle).forEach(LOGGER::debug);
        return films;
    }
}
