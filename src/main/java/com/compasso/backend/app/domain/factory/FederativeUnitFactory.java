package com.compasso.backend.app.domain.factory;

import com.compasso.backend.app.domain.entity.FederativeUnitEntity;

import java.util.Arrays;
import java.util.Collection;

public class FederativeUnitFactory {

    private FederativeUnitFactory() {
    }

    public static Collection<FederativeUnitEntity> buildDefaultFederativesUnits(){
        FederativeUnitEntity sc = new FederativeUnitEntity(1L,"SC","SANTA CATARINA");
        FederativeUnitEntity pr = new FederativeUnitEntity(2L,"PR","PARANÁ");
        FederativeUnitEntity rs = new FederativeUnitEntity(3L,"RS","RIO GRANDE DO SUL");
        FederativeUnitEntity sp = new FederativeUnitEntity(4L,"SP","SÃO PAULO");
        FederativeUnitEntity mg = new FederativeUnitEntity(5L,"MG","MINAS GERAIS");
        FederativeUnitEntity rj = new FederativeUnitEntity(6L,"RJ","RIO DE JANEIRO");
        FederativeUnitEntity es = new FederativeUnitEntity(7L,"ES","ESPÍRITO SANTO");
        FederativeUnitEntity as = new FederativeUnitEntity(8L,"AC","ACRE");
        FederativeUnitEntity al = new FederativeUnitEntity(9L,"AL","ALAGOAS");
        return Arrays.asList(sc, pr, rs, sp, mg, rj, es, as, al);
    }
}
