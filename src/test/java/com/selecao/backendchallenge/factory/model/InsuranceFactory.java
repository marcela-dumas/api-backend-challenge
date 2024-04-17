package com.selecao.backendchallenge.factory.model;

import com.selecao.backendchallenge.enums.InsuranceCategory;
import com.selecao.backendchallenge.model.Insurance;

import java.util.UUID;

public class InsuranceFactory {

    public static Insurance createInsurance() {
        return new Insurance(UUID.randomUUID(), "Seguro de Vida Individual", InsuranceCategory.LIFE, 100.0, 103.2);
    }
}
