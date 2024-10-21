package com.example.leontis.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class ObraGuia {

    @Id
    @Schema(description = "ID único da relação obra/guia",example = "12345")
    private Long id;

    @Schema(description = "Número da ordem da obra em relação ao guia",example = "1")
    @Size( max = 4, message = "O número da ordem da obra deve ter no máximo 4 números")
    @Column(name = "nr_ordem")
    private String nrOrdem;


    @Schema(description = "Descreve a localização de onde a obra se encontra dentro do museu",example = "Na segunda porta a direita da exposição da Tarsila do Amaral")
    @Size( max = 500, message = "A descrição da localização deve ter no máximo 500 caracteres")
    @Column(name = "desc_localizacao")
    private String descLocalizacao;



    @NotNull(message = "O id do guia não pode ser nulo")
    @Schema(description = "ID do guia",example = "12345")
    @Column(name = "id_guia")
    private Long idGuia;

    @NotNull(message = "O id da obra não pode ser nulo")
    @Schema(description = "ID da obra",example = "12345")
    @Column(name = "id_obra")
    private Long idObra;


}

