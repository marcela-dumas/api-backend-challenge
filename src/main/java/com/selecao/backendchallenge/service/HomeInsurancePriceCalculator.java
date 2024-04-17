package com.selecao.backendchallenge.service;

import com.selecao.backendchallenge.service.interfaces.InsurancePriceCalculator;
import org.springframework.stereotype.Service;

import static com.selecao.backendchallenge.enums.InsuranceCategory.HOME;
import static com.selecao.backendchallenge.util.GlobalConstants.multiplicadorPorcentagem;

@Service
public class HomeInsurancePriceCalculator implements InsurancePriceCalculator {

    @Override
    public Double calculateTariffedPrice(Double basePrice) {
        return basePrice + multiplicadorPorcentagem * (
                basePrice * HOME.getIOF() +
                        basePrice * HOME.getPIS() +
                        basePrice * HOME.getCOFINS());
    }
}
