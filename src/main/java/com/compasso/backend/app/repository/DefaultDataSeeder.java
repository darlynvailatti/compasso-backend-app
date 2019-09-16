package com.compasso.backend.app.repository;

import com.compasso.backend.app.App;
import com.compasso.backend.app.domain.entity.CityEntity;
import com.compasso.backend.app.domain.entity.FederativeUnitEntity;
import com.compasso.backend.app.domain.factory.CityFactory;
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

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedDefaultFederativesUnits();
        seedDefaultCities();
    }

    private void seedDefaultFederativesUnits() {
        Collection<FederativeUnitEntity> defaultFerativeUnits = FederativeUnitFactory.buildDefaultFederativesUnits();
        for (FederativeUnitEntity defaultFederativeUnit : defaultFerativeUnits) {
            federativeUnitRepository.save(defaultFederativeUnit);
        }
    }

    private void seedDefaultCities() {
        Collection<CityEntity> defaultCities = CityFactory.buildDefaultCities();
        for (CityEntity defaultCity : defaultCities) {
            cityRepository.save(defaultCity);
        }

    }
}
