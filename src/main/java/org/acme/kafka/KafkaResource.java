package org.acme.kafka;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.Hero;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class KafkaResource {

    static final Logger LOGGER = LoggerFactory.getLogger(KafkaResource.class);

    @Incoming("heroes-add")
    public void createHero(Hero hero) {
        LOGGER.debug("creating hero: {}", hero);
    }

    @Incoming("heroes-delete")
    public void deleteHero(Hero hero) {
        LOGGER.debug("deleting hero: {}", hero);
    }

    @Channel("heroes-updates")
    public void updateHero(Hero hero) {
        LOGGER.debug("updating hero: {}", hero);
    }
}
