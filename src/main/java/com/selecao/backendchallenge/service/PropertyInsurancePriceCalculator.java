package com.selecao.backendchallenge.service;

import com.selecao.backendchallenge.service.interfaces.InsurancePriceCalculator;
import org.springframework.stereotype.Service;

import static com.selecao.backendchallenge.enums.InsuranceCategory.PROPERTY;
import static com.selecao.backendchallenge.util.GlobalConstants.multiplicadorPorcentagem;

@Service
public class PropertyInsurancePriceCalculator implements InsurancePriceCalculator {

    @Override
    public Double calculateTariffedPrice(Double basePrice) {
        return basePrice + multiplicadorPorcentagem *
                (basePrice * PROPERTY.getIOF() +
                        basePrice * PROPERTY.getPIS() +
                        basePrice * PROPERTY.getCOFINS());
    }
}
