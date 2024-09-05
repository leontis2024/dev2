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
    @Schema(description = "URL da imagem",example = "https://firebasestorage.googleapis.com/v0/b/leontisfotos.appspot.com/o/usuarios%2Fuser.webp?alt=media&token=47824e40-be55-4657-a518-28528dcba3f7")
    @Column(name = "url_imagem")
    private String urlImagem;
}
