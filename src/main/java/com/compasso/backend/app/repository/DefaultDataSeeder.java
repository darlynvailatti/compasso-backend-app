package com.compasso.backend.app.repository;

import com.compasso.backend.app.App;
import com.compasso.backend.app.domain.entity.CityEntity;
import com.compasso.backend.app.domain.entity.ClientEntity;
import com.compasso.backend.app.domain.entity.FederativeUnitEntity;
import com.compasso.backend.app.domain.factory.CityFactory;
import com.compasso.backend.app.domain.factory.ClientFactory;
import com.compasso.backend.app.domain.factory.FederativeUnitFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class DefaultDataSeeder {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    @Autowired
    private IFedarativeUnitRepository federativeUnitRepository;

    @Autowired
    private ICityRepository cityRepository;

    @Autowired
    private IClientRepository clientRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedDefaultFederativesUnits();
        Collection<CityEntity> cityEntities = seedDefaultCities();

        CityEntity cityEntity = cityEntities.stream().findFirst().get();
        seedDefaultClientsForCity(cityEntity);
    }

    private Collection<FederativeUnitEntity> seedDefaultFederativesUnits() {
        Collection<FederativeUnitEntity> defaultFerativeUnits = FederativeUnitFactory.buildDefaultFederativesUnits();
        for (FederativeUnitEntity defaultFederativeUnit : defaultFerativeUnits) {
            defaultFederativeUnit = federativeUnitRepository.save(defaultFederativeUnit);
        }
        return defaultFerativeUnits;
    }

    private Collection<CityEntity> seedDefaultCities() {
        Collection<CityEntity> defaultCities = CityFactory.buildDefaultCities();
        for (CityEntity defaultCity : defaultCities) {
            defaultCity = cityRepository.save(defaultCity);
        }
        return defaultCities;
    }

    private Collection<ClientEntity> seedDefaultClientsForCity(CityEntity cityEntity) {
        Collection<ClientEntity> defaultClients = ClientFactory.buildDefaultClientsForCity(cityEntity);
        for (ClientEntity defaultClient : defaultClients) {
            defaultClient = clientRepository.save(defaultClient);
        }
        return defaultClients;
    }
}
