package com.example.leontis.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EnderecoMuseu {
    @Id
    @Schema(description = "ID único do endereço do museu",example = "12345")
    private Long id;
    @Size(max = 100,message ="A rua deve ter no maximo 100 caracteres")
    @NotNull(message = "A rua não pode ser nula")
    @Schema(description = "Nome da rua",example = "Mirante da Boa Viagem")
    private String rua;
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido")
    @NotNull(message = "O CEP não pode ser nulo")
    @Schema(description = "CEP do museu",example = "24210-390")
    private String cep;
    @NotNull(message = "O número do museu não pode ser nulo")
    @Schema(description = "Número do museu",example = "s/n°")
    @Column(name = "num_museu")
    private String numeroMuseu;
    @NotNull(message = "A cidade não pode ser nula")
    @Size(max = 50,message ="A cidade deve ter no maximo 50 caracteres")
    @Schema(description = "Cidade do museu",example = "Niterói")
    private String cidade;
    @Size(max = 2,message ="O estado deve ter no dois caracteres")
    @NotNull(message = "O estado não pode ser nulo")
    @Schema(description = "Estado do museu", example = "RJ")
    private String estado;
    @Size(max = 150,message ="O ponto de referencia deve ter no maximo 150 caracteres")
    @Schema(description = "Ponto de referencia",example = "Não tem")
    private String pontoReferencia;
}
