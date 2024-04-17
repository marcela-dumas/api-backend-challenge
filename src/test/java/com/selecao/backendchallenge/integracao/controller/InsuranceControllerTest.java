package com.selecao.backendchallenge.integracao.controller;

import com.selecao.backendchallenge.controller.InsuranceController;
import com.selecao.backendchallenge.dto.request.InsuranceRequest;
import com.selecao.backendchallenge.dto.response.InsuranceResponse;
import com.selecao.backendchallenge.enums.InsuranceCategory;
import com.selecao.backendchallenge.factory.dto.request.InsuranceRequestFactory;
import com.selecao.backendchallenge.factory.model.InsuranceFactory;
import com.selecao.backendchallenge.model.Insurance;
import com.selecao.backendchallenge.repository.InsuranceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles(value = "test")
public class InsuranceControllerTest {

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private InsuranceController insuranceController;

    private WebTestClient webTestClient;

    @BeforeEach
    public void initialize() {
        insuranceRepository.deleteAll();
        webTestClient = WebTestClient.bindToController(this.insuranceController).build();
    }

    @Test
    public void shouldntCreateInsuranceBecauseInsuranceWithSameNameExists() {

        insuranceRepository.save(InsuranceFactory.createInsurance());
        InsuranceRequest insuranceRequest = InsuranceRequestFactory.createLifeInsuranceRequestWith100BasePrice();

        webTestClient.post()
                .uri("/seguros")
                .accept(MediaType.APPLICATION_JSON)
                .body(fromValue(insuranceRequest))
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    public void shouldCreateInsurance() {

        InsuranceRequest insuranceRequest = InsuranceRequestFactory.createLifeInsuranceRequestWith100BasePrice();

        webTestClient.post()
                .uri("/seguros")
                .accept(MediaType.APPLICATION_JSON)
                .body(fromValue(insuranceRequest))
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody()
                .jsonPath("id").isNotEmpty()
                .jsonPath("nome").isEqualTo(insuranceRequest.name())
                .jsonPath("categoria").isEqualTo(InsuranceCategory.getFromValue(insuranceRequest.category()).toString())
                .jsonPath("preco_base").isEqualTo(insuranceRequest.basePrice())
                .jsonPath("preco_tarifado").isNotEmpty();
    }

    @Test
    public void shouldUpdateInsurancePrice() {

        insuranceRepository.save(InsuranceFactory.createInsurance());
        Insurance DBInsurance = insuranceRepository.findAll().get(0);
        UUID insuranceId = DBInsurance.getId();

        InsuranceRequest insuranceRequest = InsuranceRequestFactory.createLifeInsuranceRequestWith110BasePrice();

        webTestClient.put()
                .uri("/seguros/" + insuranceId)
                .accept(MediaType.APPLICATION_JSON)
                .body(fromValue(insuranceRequest))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("id").isEqualTo(DBInsurance.getId().toString())
                .jsonPath("nome").isEqualTo(DBInsurance.getName())
                .jsonPath("categoria").isEqualTo(DBInsurance.getCategory().toString())
                .jsonPath("preco_base").isEqualTo(insuranceRequest.basePrice())
                .jsonPath("preco_tarifado").isNotEmpty();
    }

    @Test
    public void shouldntUpdateInsuranceBecauseInsuranceWithThatIdDoesntExist() {

        InsuranceRequest insuranceRequest = InsuranceRequestFactory.createLifeInsuranceRequestWith110BasePrice();

        webTestClient.put()
                .uri("/seguros/" + UUID.randomUUID())
                .accept(MediaType.APPLICATION_JSON)
                .body(fromValue(insuranceRequest))
                .exchange()
                .expectStatus()
                .isNotFound();
    }

    @Test
    public void shouldFindAllInsurances() {

        insuranceRepository.save(InsuranceFactory.createInsurance());

        webTestClient.get()
                .uri("/seguros")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(InsuranceResponse.class)
                .hasSize(1);
    }
}
