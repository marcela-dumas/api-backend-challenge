package com.selecao.backendchallenge.controller;

import com.selecao.backendchallenge.dto.request.InsuranceRequest;
import com.selecao.backendchallenge.dto.response.InsuranceResponse;
import com.selecao.backendchallenge.service.interfaces.InsuranceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("seguros")
@AllArgsConstructor
public class InsuranceController {

    private final InsuranceService insuranceService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InsuranceResponse> createInsurance(@Valid @RequestBody InsuranceRequest insuranceRequest) {
       return new ResponseEntity<>(insuranceService.createInsurance(insuranceRequest), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InsuranceResponse> updateInsurance(@PathVariable UUID id, @Valid @RequestBody InsuranceRequest insuranceRequest) {
        return new ResponseEntity<>(insuranceService.updateInsurance(id, insuranceRequest), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<InsuranceResponse>> findAllInsurances() {
        return new ResponseEntity<>(insuranceService.findAllInsurances(), HttpStatus.OK);
    }
}
