package com.selecao.backendchallenge.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionMessage {
    INVALID_INSURANCE_CATEGORY("A categoria de seguro informada é inválida"),
    INSURANCE_WITH_THIS_NAME_EXISTS("Já existe um seguro cadastrado com esse nome"),
    INSURANCE_NOT_FOUND("Seguro não encontrado");

    private String message;
}
