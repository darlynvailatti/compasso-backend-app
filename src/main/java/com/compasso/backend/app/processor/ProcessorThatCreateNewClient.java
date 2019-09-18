package com.compasso.backend.app.processor;

import com.compasso.backend.app.domain.common.Gender;
import com.compasso.backend.app.domain.entity.CityEntity;
import com.compasso.backend.app.domain.entity.ClientEntity;
import com.compasso.backend.app.pattern.processor.AbstractProcessor;
import com.compasso.backend.app.processor.dto.CreateNewClientDTO;
import com.compasso.backend.app.repository.ICityRepository;
import com.compasso.backend.app.repository.IClientRepository;
import com.compasso.backend.app.util.EnsuresThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.ManagedBean;
import java.time.LocalDate;
import java.util.Optional;

@ManagedBean
public class ProcessorThatCreateNewClient extends AbstractProcessor<CreateNewClientDTO, CreateNewClientDTO.Result> {

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private ICityRepository cityRepository;

    private ClientEntity newClient;

    private ClientEntity savedClient;

    @Override
    protected void validateInput() throws Exception {
        EnsuresThat.isNotNull(input, "{0} cannot be NULL", CreateNewClientDTO.class.getSimpleName());
        ClientEntity newClient = input.getNewClient();
        EnsuresThat.isNotNull(newClient, "new Client cannot be NULL");

        LocalDate birthDate = newClient.getBirthDate();
        CityEntity city = newClient.getCity();
        String fullName = newClient.getFullName();
        Gender gender = newClient.getGender();

        EnsuresThat.isNotNull(birthDate, "Birth date cannot be empty");
        EnsuresThat.isNotNull(city, "City cannot be empty");
        EnsuresThat.isNotNull(fullName, "Full name cannot be empty");
        EnsuresThat.isNotNull(gender, "Gender cannot be empty");

    }

    @Override
    protected void executionImplementation() throws Exception {
        keepAsLocalVariable();
        ensuresThatCityExist();
        ensuresThatNotExistClientWithTheSameName();
        persistClient();
    }

    private void keepAsLocalVariable() {
        newClient = input.getNewClient();
    }

    private void ensuresThatCityExist() throws Exception {
        CityEntity city = newClient.getCity();
        Optional<CityEntity> optionalCity = cityRepository.findById(city.getId());
        EnsuresThat.isTrue(optionalCity.isPresent(), "City with ID {0} cannot be found", city.getId());
        CityEntity findedCity = optionalCity.get();
        newClient.setCity(findedCity);
    }

    private void ensuresThatNotExistClientWithTheSameName() throws Exception {
        String fullName = newClient.getFullName();
        PageRequest page = PageRequest.of(0, 1);
        Page<ClientEntity> pageClientsWithSameName = clientRepository.findByFullNameContainingIgnoreCase(fullName, page);
        boolean oneOrMoreClientsWithSameName = pageClientsWithSameName.getTotalElements() > 0;
        EnsuresThat.isFalse(oneOrMoreClientsWithSameName, "There is already a client with that name: {0}", fullName);
    }

    private void persistClient() throws Exception {
        savedClient = clientRepository.save(newClient);
        EnsuresThat.isNotNull(savedClient, "An unknow error occurs. New client cannot be persisted");
    }

    @Override
    protected CreateNewClientDTO.Result executionReturn() throws Exception {
        return new CreateNewClientDTO.Result(savedClient);
    }

    @Override
    protected String getName() {
        return ProcessorThatCreateNewClient.class.getSimpleName();
    }
}
