package com.selecao.backendchallenge.service;

import com.selecao.backendchallenge.service.interfaces.InsurancePriceCalculator;
import org.springframework.stereotype.Service;

import static com.selecao.backendchallenge.enums.InsuranceCategory.LIFE;
import static com.selecao.backendchallenge.util.GlobalConstants.multiplicadorPorcentagem;

@Service
public class LifeInsurancePriceCalculator implements InsurancePriceCalculator {

    @Override
    public Double calculateTariffedPrice(Double basePrice) {
        return basePrice + multiplicadorPorcentagem *
                (basePrice * LIFE.getIOF() +
                        basePrice * LIFE.getPIS() +
                        basePrice * LIFE.getCOFINS());
    }
}
