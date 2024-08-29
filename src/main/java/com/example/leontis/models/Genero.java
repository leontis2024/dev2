package com.example.leontis.models;

import com.example.leontis.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Genero {
    @Id
    @JsonView(Views.Resumo.class)
    private int id;

    @Size(max = 100, message = "O nomedo genero deve ter menos de 100 carcteres")
    @NotNull
    @JsonView(Views.Resumo.class)
    @Column(name = "nm_genero")
    private String nomeGenero;

    @Size(max = 500, message = "A introdução deve ter menos de 500 carcteres")
    @JsonView(Views.Resumo.class)
    private String introducao;

    @NotNull
    @JsonView(Views.Completo.class)
    @Column(name = "desc_genero")
    private String descGenero;

    @Size(max = 500, message = "A url da imagem deve ter menos de 500 carcteres")
    @JsonView(Views.Completo.class)
    @Column(name = "url_imagem")
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
