package com.example.leontis.models;

import com.example.leontis.config.Views;
import com.fasterxml.jackson.annotation.JsonView;
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
public class Genero {
    @Id
    @JsonView(Views.Resumo.class)
    @Schema(description = "Id do gênero",example = "12345")
    private int id;

    @Size(max = 100, message = "O nome do gênero deve ter menos de 100 carcteres")
    @NotNull
    @JsonView(Views.Resumo.class)
    @Column(name = "nm_genero")
    @Schema(description = "Nome do gênero",example = "Simbolismo")
    private String nomeGenero;

    @Size(max = 500, message = "A introdução deve ter menos de 500 carcteres")
    @JsonView(Views.Resumo.class)
    @Schema(description = "Introdução do gênero",example = "O neorrealismo foi movimento artístico que surgiu no início de século XX que teve influências de movimento políticos como o socialismo, o comunismo e o marxismo.")
    private String introducao;

    @NotNull
    @JsonView(Views.Completo.class)
    @Column(name = "desc_genero")
    @Schema(description = "Introdução do gênero",example = "O neorrealismo foi uma corrente artística de meados do século XX, com um carácter ideológico marcadamente de esquerda marxista, que teve ramificações em várias formas de arte (literatura, pintura, música), mas atingiu o seu expoente máximo no Cinema neorrealista, sobretudo no realismo poético francês e no neorrealismo italiano. Com o mesmo nome, mas com distinção, pode ser observada uma Teoria das relações internacionais")
    private String descGenero;

    @Size(max = 500, message = "A url da imagem deve ter menos de 500 carcteres")
    @JsonView(Views.Completo.class)
    @Column(name = "url_imagem")
    @Schema(description = "URL da imagem do gênero",example = "https://firebasestorage.googleapis.com/v0/b/leontisfotos.appspot.com/o/usuarios%2Fuser.webp?alt=media&token=47824e40-be55-4657-a518-28528dcba3f7")
    private String urlImagem;
}
