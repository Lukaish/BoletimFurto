package br.com.boletimFurto.BoletimFurto.model;

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
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "veiculo")
public class VeiculoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Informar a data")
    @Size(min=4, max = 4, message = "Ano inválido")
    private String anoFabricacao;
    @NotBlank(message = "Informar a cor")
    private String cor;
    @NotBlank(message = "Informar a marca")
    private String marca;
    @NotBlank(message = "Informar o tipo de veículo(carro, moto...)")
    private String tipoVeiculo;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Valid
    private EmplacamentoModel emplacamento;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
