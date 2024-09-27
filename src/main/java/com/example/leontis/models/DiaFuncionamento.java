package com.example.leontis.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DiaFuncionamento {

    @Id
    @Schema(description = "ID único do dia de funcionamento do museu", example = "12345")
    @NotNull(message = "ID não pode ser nulo")
    private Long id;

    @Column(name = "hr_inicio")
    @Schema(description = "Hora de início do funcionamento", example = "09:00")
    @NotBlank(message = "Hora de início não pode ser vazia")
    @Pattern(regexp = "^[0-2][0-9]:[0-5][0-9]$", message = "Hora de início deve estar no formato HH:MM")
    private String hrInicio;

    @Column(name = "hr_termino")
    @Schema(description = "Hora de término do funcionamento", example = "17:00")
    @NotBlank(message = "Hora de término não pode ser vazia")
    @Pattern(regexp = "^[0-2][0-9]:[0-5][0-9]$", message = "Hora de término deve estar no formato HH:MM")
    private String hrTermino;

    @Column(name = "pr_dia_funcionamento")
    @Schema(description = "Preço do dia de funcionamento", example = "15.50")
    @Positive(message = "Preço do dia de funcionamento deve ser um valor positivo")
    private Double precoDiaFuncionamento;

    @Column(name = "dia_semana")
    @Schema(description = "Dia da semana em que o museu funciona", example = "Segunda-feira")
    @NotBlank(message = "Dia da semana não pode ser vazio")
    @Size(max = 20, message = "Dia da semana não pode ter mais de 20 caracteres")
    private String diaSemana;

    @Column(name = "id_museu")
    @Schema(description = "ID do museu associado", example = "98765")
    @NotNull(message = "ID do museu não pode ser nulo")
    private Long idMuseu;

}
