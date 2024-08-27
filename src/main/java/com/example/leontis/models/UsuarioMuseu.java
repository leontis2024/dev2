package com.example.leontis.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

//Entidade da tabela usuarioMuseu
@Entity
public class UsuarioMuseu {
//    Id da relação usuario/museu é um long de 5 digitos
    @Id
    @Schema(description = "Id da relação Usuário/Museu ",example = "12345")
    private Long id;
//    o id do museu é um long de 5 digitos
    @Schema(description = "Id do museu",example = "12345")
    @Digits(integer = 5, fraction = 0)
    @NotNull(message = "O sobrenome não pode ser nulo")
    @Column(name = "id_museu")
    private Long idMuseu;
//    o id do usuário é um long de 5 digitos
    @Schema(description = "Id do usuário",example = "12345")
    @Digits(integer = 5, fraction = 0)
    @NotNull(message = "O sobrenome não pode ser nulo")
    @Column(name = "id_usuario")
    private Long idUsuario;

    // Construtor vazio
    public UsuarioMuseu() {
    }

    // Construtor com todos os atributos
    public UsuarioMuseu(Long id, Long idMuseu, Long idUsuario) {
        this.id = id;
        this.idMuseu = idMuseu;
        this.idUsuario = idUsuario;
    }

    //    Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMuseu() {
        return idMuseu;
    }

    public void setIdMuseu(Long idMuseu) {
        this.idMuseu = idMuseu;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    //    To string com todos os atributos
    @Override
    public String toString() {
        return "UsuarioMuseu{" +
                "id='" + id + '\'' +
                ", idMuseu='" + idMuseu + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                '}';
    }
}
