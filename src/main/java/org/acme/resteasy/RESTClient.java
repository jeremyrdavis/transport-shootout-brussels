package org.acme.resteasy;

import jakarta.ws.rs.GET;
import org.acme.domain.Film;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(configKey = "rest-client")
public interface RESTClient {

    @GET
    public List<Film> allFilms();
}