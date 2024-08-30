package com.example.leontis.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

//Entidade da tabela usuarioMuseu
@Entity
public class UsuarioGenero {
    //    Id da relação usuario/museu é um long de 5 digitos
    @Id
    @Schema(description = "Id da relação Usuário/Museu ",example = "12345")
    private Long id;
    //    o id do genero é um long de 5 digitos
    @Schema(description = "Id do genero",example = "12345")
    @Digits(integer = 5, fraction = 0)
    @NotNull(message = "O id_genero não pode ser nulo")
    @Column(name = "id_genero")
    private Long idGenero;
    //    o id do usuário é um long de 5 digitos
    @Schema(description = "Id do usuário",example = "12345")
    @Digits(integer = 5, fraction = 0)
    @NotNull(message = "O id_usuario não pode ser nulo")
    @Column(name = "id_usuario")
    private Long idUsuario;

    // Construtor vazio
    public UsuarioGenero() {
    }

    // Construtor com todos os atributos
    public UsuarioGenero(Long id, Long idUsuario, Long idGenero) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idGenero = idGenero;
    }

    //    Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id ) {
        this.id = id;
    }

    public @Digits(integer = 5 , fraction = 0) @NotNull(message = "O id_genero não pode ser nulo") Long getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(@Digits(integer = 5, fraction = 0) @NotNull(message = "O id_genero não pode ser nulo") Long idGenero) {
        this.idGenero = idGenero;
    }

    public @Digits(integer = 5, fraction = 0) @NotNull(message = "O id_usuario não pode ser nulo") Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(@Digits(integer = 5, fraction = 0) @NotNull(message = "O id_usuario não pode ser nulo") Long idUsuario) {
        this.idUsuario = idUsuario;
    }


    //    To string com todos os atributos
    @Override
    public String toString() {
        return "UsuarioGenero{" +
                "id=" + id +
                ", idGenero=" + idGenero +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
