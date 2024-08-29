package com.example.leontis.models;

import com.example.leontis.Views;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
    @Schema(description = "URL da imagem do gênero",example = "https://firebasestorage.googleapis.com/v0/b/leontisfotos.appspot.com/o/%s?alt=media")
    private String urlImagem;

    public Genero() {
    }

    public Genero(String urlImagem, String descGenero, String introducao, String nomeGenero, int id) {
        this.urlImagem = urlImagem;
        this.descGenero = descGenero;
        this.introducao = introducao;
        this.nomeGenero = nomeGenero;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @Size(max = 100, message = "O nomedo genero deve ter menos de 100 carcteres") @NotNull String getNomeGenero() {
        return nomeGenero;
    }

    public void setNomeGenero(@Size(max = 100, message = "O nomedo genero deve ter menos de 100 carcteres") @NotNull String nomeGenero) {
        this.nomeGenero = nomeGenero;
    }

    public @Size(max = 500, message = "A introdução deve ter menos de 500 carcteres") String getIntroducao() {
        return introducao;
    }

    public void setIntroducao(@Size(max = 500, message = "A introdução deve ter menos de 500 carcteres") String introducao) {
        this.introducao = introducao;
    }

    public @NotNull String getDescGenero() {
        return descGenero;
    }

    public void setDescGenero(@NotNull String descGenero) {
        this.descGenero = descGenero;
    }

    public @Size(max = 500, message = "A url da imagem deve ter menos de 500 carcteres") String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(@Size(max = 500, message = "A url da imagem deve ter menos de 500 carcteres") String urlImagem) {
        this.urlImagem = urlImagem;
    }

    @Override
    public String toString() {
        return "Genero{" +
                "id=" + id +
                ", nomeGenero='" + nomeGenero + '\'' +
                ", introducao='" + introducao + '\'' +
                ", descGenero='" + descGenero + '\'' +
                ", urlImagem='" + urlImagem + '\'' +
                '}';
    }
}
