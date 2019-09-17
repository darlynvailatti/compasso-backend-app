package com.compasso.backend.app.domain.factory;

import com.compasso.backend.app.domain.common.Gender;
import com.compasso.backend.app.domain.entity.CityEntity;
import com.compasso.backend.app.domain.entity.ClientEntity;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Collection;

public class ClientFactory {

    private ClientFactory(){}

    public static Collection<ClientEntity> buildDefaultClientsForCity(CityEntity cityEntity){

        ClientEntity c1 = new ClientEntity();
        c1.setId(1L);
        c1.setGender(Gender.MALE);
        c1.setBirthDate(LocalDate.now().minus(Period.ofYears(350)));
        c1.setCity(cityEntity);
        c1.setFullName("Gandalf The Mage");

        ClientEntity c2 = new ClientEntity();
        c2.setId(2L);
        c2.setGender(Gender.FEMALE);
        c2.setBirthDate(LocalDate.now().minus(Period.ofYears(560)));
        c2.setCity(cityEntity);
        c2.setFullName("Galadriel The Elf");

        return Arrays.asList(c1, c2);

    }
}
