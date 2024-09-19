package com.example.leontis.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//Entidade da tabela artistaGenero
@Entity
public class ArtistaGenero {
    //    Id da relação artista/genero é um long de 5 digitos
    @Id
    @Schema(description = "Id da relação Artista/Genero ",example = "12345")
    private Long id;
    //    o id do genero é um long de 5 digitos
    @Schema(description = "Id do genero",example = "12345")
    @Digits(integer = 5, fraction = 0)
    @NotNull(message = "O id_genero não pode ser nulo")
    @Column(name = "id_genero")
    private Long idGenero;
    //    o id do usuário é um long de 5 digitos
    @Schema(description = "Id do artista",example = "12345")
    @Digits(integer = 5, fraction = 0)
    @NotNull(message = "O id_artista não pode ser nulo")
    @Column(name = "id_artista")
    private Long idArtista;
}
