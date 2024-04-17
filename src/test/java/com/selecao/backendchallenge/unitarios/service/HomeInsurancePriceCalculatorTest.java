package com.selecao.backendchallenge.unitarios.service;

import com.selecao.backendchallenge.service.HomeInsurancePriceCalculator;
import com.selecao.backendchallenge.service.interfaces.InsurancePriceCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HomeInsurancePriceCalculatorTest {

    InsurancePriceCalculator insurancePriceCalculator = new HomeInsurancePriceCalculator();

    @Test
    void calculateTariffedPrice() {

        Double response = insurancePriceCalculator.calculateTariffedPrice(100.0);
        Assertions.assertEquals(107.0, response);
    }
}
