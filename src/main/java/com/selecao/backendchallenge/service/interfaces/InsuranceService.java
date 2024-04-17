package com.selecao.backendchallenge.service.interfaces;

import com.selecao.backendchallenge.dto.request.InsuranceRequest;
import com.selecao.backendchallenge.dto.response.InsuranceResponse;

import java.util.List;
import java.util.UUID;

public interface InsuranceService {
    InsuranceResponse createInsurance(InsuranceRequest insuranceRequest);
    InsuranceResponse updateInsurance(UUID id, InsuranceRequest insuranceRequest);
    List<InsuranceResponse> findAllInsurances();
}
