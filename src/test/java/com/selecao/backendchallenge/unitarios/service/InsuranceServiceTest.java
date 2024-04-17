package com.selecao.backendchallenge.unitarios.service;

import com.selecao.backendchallenge.dto.request.InsuranceRequest;
import com.selecao.backendchallenge.dto.response.InsuranceResponse;
import com.selecao.backendchallenge.enums.InsuranceCategory;
import com.selecao.backendchallenge.factory.dto.request.InsuranceRequestFactory;
import com.selecao.backendchallenge.factory.model.InsuranceFactory;
import com.selecao.backendchallenge.model.Insurance;
import com.selecao.backendchallenge.repository.InsuranceRepository;
import com.selecao.backendchallenge.service.InsuranceServiceImpl;
import com.selecao.backendchallenge.service.interfaces.InsuranceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InsuranceServiceTest {

    @Mock
    InsuranceRepository insuranceRepository;

    InsuranceService insuranceService;

    @BeforeEach
    public void initialize() {
        insuranceService = new InsuranceServiceImpl(insuranceRepository);
    }

    @Test
    void shouldCreateLifeInsurance() {

        InsuranceRequest insuranceRequest = InsuranceRequestFactory.createLifeInsuranceRequestWith100BasePrice();

        when(insuranceRepository.findByName(insuranceRequest.name())).thenReturn(Optional.empty());

        InsuranceResponse insuranceResponse = insuranceService.createInsurance(insuranceRequest);

        assertNotNull(insuranceResponse.id());
        assertEquals(insuranceRequest.name(), insuranceResponse.name());
        assertEquals(InsuranceCategory.LIFE.toString(), insuranceResponse.category());
        assertEquals(insuranceRequest.basePrice(), insuranceResponse.basePrice());
        assertNotNull(insuranceResponse.tariffedPrice());
    }

    @Test
    void shouldCreateAutoInsurance() {

        InsuranceRequest insuranceRequest = InsuranceRequestFactory.createAutoInsuranceRequest();

        when(insuranceRepository.findByName(insuranceRequest.name())).thenReturn(Optional.empty());

        InsuranceResponse insuranceResponse = insuranceService.createInsurance(insuranceRequest);

        assertEquals(InsuranceCategory.AUTO.toString(), insuranceResponse.category());
    }

    @Test
    void shouldCreateTravelInsurance() {

        InsuranceRequest insuranceRequest = InsuranceRequestFactory.createTravelInsuranceRequest();

        when(insuranceRepository.findByName(insuranceRequest.name())).thenReturn(Optional.empty());

        InsuranceResponse insuranceResponse = insuranceService.createInsurance(insuranceRequest);

        assertEquals(InsuranceCategory.TRAVEL.toString(), insuranceResponse.category());
    }

    @Test
    void shouldCreatePropertyInsurance() {

        InsuranceRequest insuranceRequest = InsuranceRequestFactory.createPropertyInsuranceRequest();

        when(insuranceRepository.findByName(insuranceRequest.name())).thenReturn(Optional.empty());

        InsuranceResponse insuranceResponse = insuranceService.createInsurance(insuranceRequest);

        assertEquals(InsuranceCategory.PROPERTY.toString(), insuranceResponse.category());
    }

    @Test
    void shouldCreateHomeInsurance() {

        InsuranceRequest insuranceRequest = InsuranceRequestFactory.createHomeInsuranceRequest();

        when(insuranceRepository.findByName(insuranceRequest.name())).thenReturn(Optional.empty());

        InsuranceResponse insuranceResponse = insuranceService.createInsurance(insuranceRequest);

        assertEquals(InsuranceCategory.HOME.toString(), insuranceResponse.category());
    }

    @Test
    void shouldntCreateInsuranceBecauseInsuranceWithSameNameExists() {

        InsuranceRequest insuranceRequest = InsuranceRequestFactory.createLifeInsuranceRequestWith100BasePrice();
        when(insuranceRepository.findByName(insuranceRequest.name())).thenReturn(Optional.of(InsuranceFactory.createInsurance()));
        assertThrows(ResponseStatusException.class, () -> insuranceService.createInsurance(insuranceRequest));
    }

    @Test
    void shouldUpdateInsurance() {

        Insurance insurance = InsuranceFactory.createInsurance();
        InsuranceRequest insuranceRequest = InsuranceRequestFactory.createLifeInsuranceRequestWith110BasePrice();
        when(insuranceRepository.findById(insurance.getId())).thenReturn(Optional.of(insurance));

        InsuranceResponse insuranceResponse = insuranceService.updateInsurance(insurance.getId(), insuranceRequest);

        assertNotNull(insurance.getId().toString(), insuranceResponse.id());
        assertEquals(insuranceRequest.name(), insuranceResponse.name());
        assertEquals(InsuranceCategory.LIFE.toString(), insuranceResponse.category());
        assertEquals(insuranceRequest.basePrice(), insuranceResponse.basePrice());
        assertNotNull(insuranceResponse.tariffedPrice());
    }

    @Test
    void shouldntUpdateInsuranceThatDoesntExist() {

        InsuranceRequest insuranceRequest = InsuranceRequestFactory.createLifeInsuranceRequestWith100BasePrice();
        Insurance insurance = InsuranceFactory.createInsurance();

        when(insuranceRepository.findById(insurance.getId())).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> insuranceService.updateInsurance(insurance.getId(), insuranceRequest));
    }
}
