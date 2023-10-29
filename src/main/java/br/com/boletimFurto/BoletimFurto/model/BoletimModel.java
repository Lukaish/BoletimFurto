package br.com.boletimFurto.BoletimFurto.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
@Entity(name = "boletim")
public class BoletimModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identificador;

    @Column
    @NotNull(message = "Informar a data")
    @PastOrPresent(message = "Data inválida")
    private LocalDate dataOcorrencia;
    @NotBlank(message = "Informar o período")
    private String periodoOcorrencia;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Valid
    private EnderecoModel enderecoModel;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Valid
    private VeiculoModel veiculoModel;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Valid
    private ParteModel parteModel;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
