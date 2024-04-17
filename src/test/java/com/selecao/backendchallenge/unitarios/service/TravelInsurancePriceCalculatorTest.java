package com.selecao.backendchallenge.unitarios.service;

import com.selecao.backendchallenge.service.TravelInsurancePriceCalculator;
import com.selecao.backendchallenge.service.interfaces.InsurancePriceCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TravelInsurancePriceCalculatorTest {

    InsurancePriceCalculator insurancePriceCalculator = new TravelInsurancePriceCalculator();

    @Test
    void calculateTariffedPrice() {

        Double response = insurancePriceCalculator.calculateTariffedPrice(100.0);
        Assertions.assertEquals(107.0, response);
    }
}
