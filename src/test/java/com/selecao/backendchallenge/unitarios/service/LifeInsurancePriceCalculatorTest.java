package com.selecao.backendchallenge.unitarios.service;

import com.selecao.backendchallenge.service.LifeInsurancePriceCalculator;
import com.selecao.backendchallenge.service.interfaces.InsurancePriceCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LifeInsurancePriceCalculatorTest {

    InsurancePriceCalculator insurancePriceCalculator = new LifeInsurancePriceCalculator();

    @Test
    void calculateTariffedPrice() {

        Double response = insurancePriceCalculator.calculateTariffedPrice(100.0);
        Assertions.assertEquals(103.20, response);
    }
}
