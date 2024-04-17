package com.selecao.backendchallenge.repository;

import com.selecao.backendchallenge.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, UUID> {
    Optional<Insurance> findByName(String name);
}
