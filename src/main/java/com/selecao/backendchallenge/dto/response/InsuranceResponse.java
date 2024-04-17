package com.selecao.backendchallenge.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.selecao.backendchallenge.model.Insurance;

public record InsuranceResponse(String id,
                                @JsonProperty("nome") String name,
                                @JsonProperty("categoria") String category,
                                @JsonProperty("preco_base") Double basePrice,
                                @JsonProperty("preco_tarifado") Double tariffedPrice) {
    public InsuranceResponse(Insurance insurance) {
        this(String.valueOf(insurance.getId()),
                insurance.getName(),
                insurance.getCategory().toString(),
                insurance.getBasePrice(),
                insurance.getTariffedPrice());
    }
}
