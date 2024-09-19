package com.example.leontis.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
    @NotNull(message = "O id_museu não pode ser nulo")
    @Column(name = "id_museu")
    private Long idMuseu;
//    o id do usuário é um long de 5 digitos
    @Schema(description = "Id do usuário",example = "12345")
    @Digits(integer = 5, fraction = 0)
    @NotNull(message = "O id_usuario não pode ser nulo")
    @Column(name = "id_usuario")
    private Long idUsuario;


}
