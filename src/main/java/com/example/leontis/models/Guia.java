package com.example.leontis.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Guia {

//     Id do guia é um long de cinco digitos
    @Id
    @Schema(description = "ID único do guia",example = "12345")
    private Long id;

// o titulo do guia é uma string not null de no maximo 100 caracteres
    @NotNull(message = "O titulo do guia não pode ser nulo")
    @Size(max = 100,message = "O titulo do guia deve ter no maximo 100 caracteres")
    @Schema(description = "Titulo do guia",example = "Obras da Tarcila do Amaral")
    @Column(name = "titulo_guia")
    private String tituloGuia;

//    a descrição do guia é uma string
    @Schema(description = "Descrição do guia",example = "Este guia passa por todas as obras da Tarcila do Amaral pela ordem cronologica de suas criações presente neste museu.")
    @Column(name = "desc_guia")
    private String descGuia;

//    o id do museu é um long que cria a relação entre a obra e o museu
    @NotNull(message = "O id do museu não deve ser nulo")
    @Column(name = "id_museu")
    private Long idMuseu;

    // a url é o caminho da imagem no firebase
    @Size(max=500,message = "A URL da imagem deve ter no máximo 500 caracteres")
    @Schema(description = "URL da imagem",example = "https://firebasestorage.googleapis.com/v0/b/leontisfotos.appspot.com/o/%s?alt=media")
    @Column(name = "url_imagem")
    private String urlImagem;

    // Construtor vazio
    public Guia() {
    }

    // Construtor com todos os atributos
    public Guia(String urlImagem, Long idMuseu, String descGuia, String tituloGuia, Long id) {
        this.urlImagem = urlImagem;
        this.idMuseu = idMuseu;
        this.descGuia = descGuia;
        this.tituloGuia = tituloGuia;
        this.id = id;
    }
    //    Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "O titulo do guia não pode ser nulo") @Size(max = 100, message = "O titulo do guia deve ter no maximo 100 caracteres") String getTituloGuia() {
        return tituloGuia;
    }

    public void setTituloGuia(@NotNull(message = "O titulo do guia não pode ser nulo") @Size(max = 100, message = "O titulo do guia deve ter no maximo 100 caracteres") String tituloGuia) {
        this.tituloGuia = tituloGuia;
    }

    public String getDescGuia() {
        return descGuia;
    }

    public void setDescGuia(String descGuia) {
        this.descGuia = descGuia;
    }

    public @NotNull(message = "O id do museu não deve ser nulo") Long getIdMuseu() {
        return idMuseu;
    }

    public void setIdMuseu(@NotNull(message = "O id do museu não deve ser nulo") Long idMuseu) {
        this.idMuseu = idMuseu;
    }

    public @Size(max = 500, message = "A URL da imagem deve ter no máximo 500 caracteres") String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(@Size(max = 500, message = "A URL da imagem deve ter no máximo 500 caracteres") String urlImagem) {
        this.urlImagem = urlImagem;
    }

    @Override
    public String toString() {
        return "Guia{" +
                "id=" + id +
                ", tituloGuia='" + tituloGuia + '\'' +
                ", descGuia='" + descGuia + '\'' +
                ", idMuseu=" + idMuseu +
                ", urlImagem='" + urlImagem + '\'' +
                '}';
    }
}
