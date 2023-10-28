package br.com.boletimFurto.BoletimFurto.model;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "emplacamento")
public class EmplacamentoModel {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Informar a placa")
    @Pattern(regexp = "[A-Z]{3}[0-9]{1}[A-Z]{1}[0-9]{2}|[A-Z]{3}[0-9]{4}", message = "Placa inválida...")
    private String placa;
    @NotBlank(message = "Informar a cidade")
    private String cidade;
    @NotBlank(message = "Informar o estado")
    private String estado;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
