package com.example.leontis.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//Entidade da tabela obra
@Entity
public class Obra {
    @Id
    @Schema(description = "ID único da obra",example = "12345")
    private Long id;
    @Column(name = "ano_inicio")
    private String anoInicio;
    @Column(name = "ano_final")
    private String anoFinal;
    @Column(name = "desc_obra")
    private String descObra;
    @Column(name = "nm_obra")
    private String nomeObra;
    @Column(name = "id_genero")
    private Long idGenero;
    @Column(name = "id_artista")
    private Long idArtista;
    @Column(name = "id_museu")
    private Long idMuseu;
    @Column(name = "ulr_imagem")
    private String ulrImagem;

    public Obra() {
    }

    public Obra(long id, String anoInicio, String anoFinal, String descObra, String nomeObra, long idGenero, long idArtista, long idMuseu, String ulrImagem) {
        this.id = id;
        this.anoInicio = anoInicio;
        this.anoFinal = anoFinal;
        this.descObra = descObra;
        this.nomeObra = nomeObra;
        this.idGenero = idGenero;
        this.idArtista = idArtista;
        this.idMuseu = idMuseu;
        this.ulrImagem = ulrImagem;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(String anoInicio) {
        this.anoInicio = anoInicio;
    }

    public String getAnoFinal() {
        return anoFinal;
    }

    public void setAnoFinal(String anoFinal) {
        this.anoFinal = anoFinal;
    }

    public String getDescObra() {
        return descObra;
    }

    public void setDescObra(String descObra) {
        this.descObra = descObra;
    }

    public String getNomeObra() {
        return nomeObra;
    }

    public void setNomeObra(String nomeObra) {
        this.nomeObra = nomeObra;
    }

    public long getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(long idGenero) {
        this.idGenero = idGenero;
    }

    public long getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(long idArtista) {
        this.idArtista = idArtista;
    }

    public long getIdMuseu() {
        return idMuseu;
    }

    public void setIdMuseu(long idMuseu) {
        this.idMuseu = idMuseu;
    }

    public String getUlrImagem() {
        return ulrImagem;
    }

    public void setUlrImagem(String ulrImagem) {
        this.ulrImagem = ulrImagem;
    }

    @Override
    public String toString() {
        return "Obra{" +
                "id=" + id +
                ", anoInicio='" + anoInicio + '\'' +
                ", anoFinal='" + anoFinal + '\'' +
                ", descObra='" + descObra + '\'' +
                ", nomeObra='" + nomeObra + '\'' +
                ", idGenero=" + idGenero +
                ", idArtista=" + idArtista +
                ", idMuseu=" + idMuseu +
                ", ulrImagem='" + ulrImagem + '\'' +
                '}';
    }
}
