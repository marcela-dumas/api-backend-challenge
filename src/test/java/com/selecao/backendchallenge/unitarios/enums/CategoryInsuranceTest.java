package com.selecao.backendchallenge.unitarios.enums;

import com.selecao.backendchallenge.enums.InsuranceCategory;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CategoryInsuranceTest {

    @Test
    void shouldThrowExceptionForInvalidCategory() {
        assertThrows(ResponseStatusException.class, () -> InsuranceCategory.getFromValue("CARRO"));
    }
}
