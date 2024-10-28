package com.example.leontis.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//Entidade da tabela obra
@Entity
public class MuseuAdm {
    @Id
    @Schema(description = "Id do museu adm",example = "12345")
    private Long id;

    // o email é uma string not null que deve estar no formato email@email.com e não pode ser nula
    @NotNull(message = "O email não pode ser nulo")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    @Column(name = "email_adm")
    @Schema(description = "Email do adm museu",example = "museu2@gmail.com")
    private String emailAdm;

    // a senha é uma string not null que deve ter no minimo 3 caracteres e no maximo 100
    @NotNull(message = "A senha não pode ser nula")
    @Size(min = 5, message = "A senha deve ter pelo menos 3 carcteres")
    @Size(max = 100, message = "A senha deve ter menos de 100 carcteres")
    @Column(name = "senha_adm")
    @Schema(description = "Senha da conta do museu adm",example = "1234Senha")
    private String senhaAdm;
}
