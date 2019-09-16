package com.compasso.backend.app.domain.factory;

import com.compasso.backend.app.domain.entity.CityEntity;
import com.compasso.backend.app.domain.entity.FederativeUnitEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class CityFactory {

    private CityFactory(){}

    public static Collection<CityEntity> buildDefaultCities(){
        Collection<FederativeUnitEntity> federativeUnitEntities = FederativeUnitFactory.buildDefaultFederativesUnits();
        FederativeUnitEntity sc = federativeUnitEntities.stream()
                .filter(f -> f.getInitials().equalsIgnoreCase("sc"))
                .findFirst()
                .get();
        CityEntity chapeco = new CityEntity("Chapecó", sc);
        CityEntity xanxere = new CityEntity("Xanxerê", sc);
        CityEntity xaxim = new CityEntity("Xaxim", sc);
        CityEntity quilombo = new CityEntity("Quilombo", sc);
        CityEntity uniaoDoOeste = new CityEntity("União do Oeste", sc);
        return Arrays.asList(chapeco, xanxere, xaxim, quilombo, uniaoDoOeste);
    }

}
