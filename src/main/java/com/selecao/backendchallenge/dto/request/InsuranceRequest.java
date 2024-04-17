package com.selecao.backendchallenge.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record InsuranceRequest(
        @NotBlank(message = "O campo name é obrigatório")
        @Size(min = 1, max = 40, message = "O campo name deve ter no mínimo {min} no máximo {max} caracteres")
        @JsonProperty("nome") String name,

        @NotBlank(message = "O campo category é obrigatório")
        @Size(min = 1, max = 20, message = "O campo ca" +
                "tegory deve ter no mínimo {min} no máximo {max} caracteres")
        @JsonProperty("categoria") String category,

        @NotNull(message = "O campo preco_base é obrigatório") @JsonProperty("preco_base") Double basePrice) {
}