package com.selecao.backendchallenge.service;

import com.selecao.backendchallenge.service.interfaces.InsurancePriceCalculator;
import org.springframework.stereotype.Service;

import static com.selecao.backendchallenge.enums.InsuranceCategory.TRAVEL;
import static com.selecao.backendchallenge.util.GlobalConstants.multiplicadorPorcentagem;

@Service
public class TravelInsurancePriceCalculator implements InsurancePriceCalculator {

    @Override
    public Double calculateTariffedPrice(Double basePrice) {
        return basePrice + multiplicadorPorcentagem * (
                basePrice * TRAVEL.getIOF() +
                        basePrice * TRAVEL.getPIS() +
                        basePrice * TRAVEL.getCOFINS());
    }
}
