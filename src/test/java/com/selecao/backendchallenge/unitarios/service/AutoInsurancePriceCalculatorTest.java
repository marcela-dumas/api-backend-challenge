package com.selecao.backendchallenge.unitarios.service;

import com.selecao.backendchallenge.service.AutoInsurancePriceCalculator;
import com.selecao.backendchallenge.service.interfaces.InsurancePriceCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AutoInsurancePriceCalculatorTest {

    InsurancePriceCalculator insurancePriceCalculator = new AutoInsurancePriceCalculator();

    @Test
    void calculateTariffedPrice() {
        Double response = insurancePriceCalculator.calculateTariffedPrice(50.0);
        Assertions.assertEquals(55.25, response);
    }
}
