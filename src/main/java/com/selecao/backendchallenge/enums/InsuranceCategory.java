package com.selecao.backendchallenge.enums;

import com.selecao.backendchallenge.exception.ExceptionMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
@AllArgsConstructor
public enum InsuranceCategory {
    LIFE(1.0, 2.2, 0.0),
    AUTO(5.5, 4.0, 1.0),
    TRAVEL(2.0, 4.0, 1.0),
    HOME(4.0, 0.0, 3.0),
    PROPERTY(5.0, 3.0, 0.0);

    private final Double IOF;
    private final Double PIS;
    private final Double COFINS;

    public static InsuranceCategory getFromValue(String value) {
        return switch (value.toUpperCase()) {
            case "VIDA" -> LIFE;
            case "AUTO" -> AUTO;
            case "RESIDENCIAL" -> HOME;
            case "PATRIMONIAL" -> PROPERTY;
            case "VIAGEM" -> TRAVEL;
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ExceptionMessage.INVALID_INSURANCE_CATEGORY.getMessage());
        };
    }
}