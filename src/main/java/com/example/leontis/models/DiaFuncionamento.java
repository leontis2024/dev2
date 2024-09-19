package com.example.leontis.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DiaFuncionamento {
    @Id
    @Schema(description = "ID único do dia de funcionamento do museu",example = "12345")
    private Long id;
    @Column(name = "hr_inicio")
    private String hrInicio;
    @Column(name = "hr_termino")
    private String hrTermino;
    @Column(name = "pr_dia_funcionamento")
    private double precoDiaFuncionamento;
    @Column(name = "dia_semana")
    private String diaSemana;
    @Column(name = "id_museu")
    private Long idMuseu;

}
