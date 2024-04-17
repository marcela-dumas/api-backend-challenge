package com.selecao.backendchallenge.service;

import com.selecao.backendchallenge.service.interfaces.InsurancePriceCalculator;
import org.springframework.stereotype.Service;

import static com.selecao.backendchallenge.enums.InsuranceCategory.AUTO;
import static com.selecao.backendchallenge.util.GlobalConstants.multiplicadorPorcentagem;

@Service
public class AutoInsurancePriceCalculator implements InsurancePriceCalculator {

    @Override
    public Double calculateTariffedPrice(Double basePrice) {
        return basePrice + multiplicadorPorcentagem * (
                basePrice * AUTO.getIOF() +
                        basePrice * AUTO.getPIS() +
                        basePrice * AUTO.getCOFINS());
    }
}
