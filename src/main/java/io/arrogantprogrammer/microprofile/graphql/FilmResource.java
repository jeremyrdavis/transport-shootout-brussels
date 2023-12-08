package io.arrogantprogrammer.microprofile.graphql;

import io.arrogantprogrammer.domain.Film;
import io.arrogantprogrammer.domain.Hero;
import io.smallrye.graphql.api.Subscription;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.operators.multi.processors.BroadcastProcessor;
import io.arrogantprogrammer.domain.GalaxyService;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.DefaultValue;
import org.eclipse.microprofile.graphql.Source;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@GraphQLApi
@ApplicationScoped
public class FilmResource {

    @Inject
    GalaxyService service;

    private final BroadcastProcessor<Hero> processor = BroadcastProcessor.create();

    @Query("allFilms")
    @Description("Get all Films from a galaxy far far away")
    public List<Film> getAllFilms() {
        return service.getAllFilms();
    }

    @Query
    @Description("Get a Film from a galaxy far far away")
    public Film getFilm(@Name("filmId") int id) {
        return service.getFilm(id);
    }

    public List<Hero> heroes(@Source Film film) {
        return service.getHeroesByFilm(film);
    }

    @Mutation
    public Hero createHero(@Name("HeroInput") Hero hero) {
        service.addHero(hero);
        processor.onNext(hero);
        return hero;
    }

    @Mutation
    public Hero deleteHero(int id) {
        return service.deleteHero(id);
    }

    @Query
    public List<Hero> getHeroesWithSurname(@DefaultValue("Skywalker") String surname) {
        return service.getHeroesBySurname(surname);
    }

    @Query
    public List<Hero> allHeroes() {
        return service.getHeroes();
    }


    @Subscription
    public Multi<Hero> heroCreated() {
        return processor;
    }

}
