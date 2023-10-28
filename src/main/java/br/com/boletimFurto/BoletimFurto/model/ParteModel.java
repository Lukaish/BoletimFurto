package br.com.boletimFurto.BoletimFurto.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "parte")
public class ParteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Informar o nome")
    private String nome;

    @Email(message = "Email inválido")
    @NotBlank(message = "Informar o email")
    private String email;

    @NotBlank(message = "Informar o telefone")
    @Size(min = 11, max = 13, message = "Telefone incompleto, talvez falte o DDD...")
    @Pattern(regexp = "^\\(?[1-9]{2}\\)? ?(?:[2-8]|9[0-9])[0-9]{3}\\-?[0-9]{4}$", message = "Número de telefone inválido")
    private String telefone;

    @NotBlank(message = "Informar o crime")
    private String tipoEnvolvimento;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
