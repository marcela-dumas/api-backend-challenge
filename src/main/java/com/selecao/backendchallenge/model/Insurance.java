package com.selecao.backendchallenge.model;

import com.selecao.backendchallenge.enums.InsuranceCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Table(name = "seguro")
@EntityListeners(AuditingEntityListener.class)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Insurance {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(name ="nome")
    private String name;

    @Column(name ="categoria")
    @Enumerated(EnumType.STRING)
    private InsuranceCategory category;

    @Column(name ="preco_base")
    private Double basePrice;

    @Column(name ="preco_tarifado")
    private Double tariffedPrice;
}
