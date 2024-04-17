package com.selecao.backendchallenge.unitarios.service;

import com.selecao.backendchallenge.service.PropertyInsurancePriceCalculator;
import com.selecao.backendchallenge.service.interfaces.InsurancePriceCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PropertyInsuranceCalculatorTest {

    InsurancePriceCalculator insurancePriceCalculator = new PropertyInsurancePriceCalculator();

    @Test
    void calculateTariffedPrice() {

        Double response = insurancePriceCalculator.calculateTariffedPrice(100.0);
        Assertions.assertEquals(108.0, response);
    }
}
