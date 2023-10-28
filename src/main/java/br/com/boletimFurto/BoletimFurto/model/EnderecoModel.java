package br.com.boletimFurto.BoletimFurto.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity(name = "endereco")
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Informar o logradouro")
    private String logradouro;
    @NotBlank(message = "Informar o número")
    private String numero;
    @NotBlank(message = "Informar o bairro")
    private String bairro;
    @NotBlank(message = "Informar a cidade")
    private String cidade;
    @NotBlank(message = "Informar o estado")
    private String estado;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
