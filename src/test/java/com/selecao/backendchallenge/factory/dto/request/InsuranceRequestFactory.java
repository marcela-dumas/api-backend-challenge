package com.selecao.backendchallenge.factory.dto.request;

import com.selecao.backendchallenge.dto.request.InsuranceRequest;

public class InsuranceRequestFactory {
    public static InsuranceRequest createLifeInsuranceRequestWith100BasePrice() {
        return new InsuranceRequest("Seguro de Vida Individual", "VIDA", 100.0);
    }

    public static InsuranceRequest createAutoInsuranceRequest() {
        return new InsuranceRequest("Seguro Auto", "AUTO", 100.0);
    }

    public static InsuranceRequest createHomeInsuranceRequest() {
        return new InsuranceRequest("Seguro Residencial", "RESIDENCIAL", 100.0);
    }

    public static InsuranceRequest createPropertyInsuranceRequest() {
        return new InsuranceRequest("Seguro Patrimonial", "PATRIMONIAL", 100.0);
    }

    public static InsuranceRequest createTravelInsuranceRequest() {
        return new InsuranceRequest("Seguro Patrimonial", "VIAGEM", 100.0);
    }

    public static InsuranceRequest createLifeInsuranceRequestWith110BasePrice() {
        return new InsuranceRequest("Seguro de Vida Individual", "VIDA", 110.0);
    }
}
