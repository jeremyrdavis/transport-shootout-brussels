package io.arrogantprogrammer.resteasy;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import io.arrogantprogrammer.domain.GalaxyService;
import io.arrogantprogrammer.domain.Hero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.arrogantprogrammer.domain.Film;

import java.util.List;

@Path("/rest")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RESTResource {

    static final Logger LOGGER = LoggerFactory.getLogger(RESTResource.class);

    @Inject
    GalaxyService galaxyService;

    @GET
    public List<Film> allFilms() {
        return galaxyService.getAllFilms();
    }

    @GET
    @Path("/{id}")
    public Film getFilm(@PathParam("id") Integer id) {
        return galaxyService.getFilm(id);
    }

    @POST
    public Hero createHero(Hero hero) {
        galaxyService.addHero(hero);
        return hero;
    }

    @DELETE
    @Path("/{id}")
    public Hero deleteHero(@PathParam("id") Integer id) {
        return galaxyService.deleteHero(id);
    }

    @GET
    @Path("/heroes/surname/{surname}")
    public List<Hero> getHeroesWithSurname(@PathParam("surname") String surname) {
        return galaxyService.getHeroesBySurname(surname);
    }

    @GET
    @Path("/heroes")
    public List<Hero> getHeroes() {
        return galaxyService.getHeroes();
    }

}
