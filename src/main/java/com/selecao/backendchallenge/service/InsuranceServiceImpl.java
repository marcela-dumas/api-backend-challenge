package com.selecao.backendchallenge.service;

import com.selecao.backendchallenge.dto.request.InsuranceRequest;
import com.selecao.backendchallenge.dto.response.InsuranceResponse;
import com.selecao.backendchallenge.enums.InsuranceCategory;
import com.selecao.backendchallenge.exception.ExceptionMessage;
import com.selecao.backendchallenge.model.Insurance;
import com.selecao.backendchallenge.repository.InsuranceRepository;
import com.selecao.backendchallenge.service.interfaces.InsurancePriceCalculator;
import com.selecao.backendchallenge.service.interfaces.InsuranceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;

    @Override
    public InsuranceResponse createInsurance(InsuranceRequest insuranceRequest) {

        log.info("Método criaSeguro chamado - name: {}, category: {}, preco base: {}", insuranceRequest.name(),
                insuranceRequest.category(), insuranceRequest.basePrice());

        checkIfExistsInsuranceWithThisName(insuranceRequest.name());

        InsuranceCategory insuranceCategory = InsuranceCategory.getFromValue(insuranceRequest.category());

        InsurancePriceCalculator insurancePriceCalculator = getInsurancePriceCalculator(insuranceCategory);

        Double tariffedPrice = insurancePriceCalculator.calculateTariffedPrice(insuranceRequest.basePrice());

        Insurance insurance = Insurance.builder()
                .name(insuranceRequest.name())
                .category(insuranceCategory)
                .basePrice(insuranceRequest.basePrice())
                .tariffedPrice(tariffedPrice)
                .build();

        insuranceRepository.save(insurance);

        log.info("Novo seguro criado com sucesso - name: {}, category: {}, preco base: {}, preco tarifado: {}", insurance.getName(),
                insurance.getCategory(), insurance.getBasePrice(), insurance.getTariffedPrice());

        return new InsuranceResponse(insurance);
    }

    private static InsurancePriceCalculator getInsurancePriceCalculator(InsuranceCategory insuranceCategory) {
        return switch (insuranceCategory) {
            case LIFE -> new LifeInsurancePriceCalculator();
            case AUTO -> new AutoInsurancePriceCalculator();
            case TRAVEL -> new TravelInsurancePriceCalculator();
            case HOME -> new HomeInsurancePriceCalculator();
            case PROPERTY -> new PropertyInsurancePriceCalculator();
        };
    }

    @Override
    public InsuranceResponse updateInsurance(UUID id, InsuranceRequest insuranceRequest) {

        log.info("Método atualizaSeguro chamado - id: {}, preco base: {}", id, insuranceRequest.basePrice());

        Insurance insurance = findById(id);
        insurance.setBasePrice(insuranceRequest.basePrice());

        InsurancePriceCalculator insurancePriceCalculator = getInsurancePriceCalculator(insurance.getCategory());

        Double tariffedPrice = insurancePriceCalculator.calculateTariffedPrice(insurance.getBasePrice());
        insurance.setTariffedPrice(tariffedPrice);
        insuranceRepository.save(insurance);

        log.info("Seguro atualizado com sucesso - name: {}, category: {}, preco base: {}, preco tarifado: {}", insurance.getName(),
                insurance.getCategory(), insurance.getBasePrice(), insurance.getTariffedPrice());

        return new InsuranceResponse(insurance);
    }

    @Override
    public List<InsuranceResponse> findAllInsurances() {

        log.debug("Método buscaSeguros chamado");

        List<Insurance> insuranceList = insuranceRepository.findAll();
        return insuranceList.stream().map(InsuranceResponse::new).collect(Collectors.toList());
    }

    private Insurance findById(UUID id) {

        log.debug("Método buscaPeloId chamado - id: {}", id);

        return insuranceRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionMessage.INSURANCE_NOT_FOUND.getMessage()));
    }

    private void checkIfExistsInsuranceWithThisName(String insuranceName) {
        insuranceRepository.findByName(insuranceName).ifPresent(value -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ExceptionMessage.INSURANCE_WITH_THIS_NAME_EXISTS.getMessage());
        });
    }
}