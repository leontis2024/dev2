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
//Entidade da tabela obra
@Entity
public class Obra {
    //    Id da Obra é um long de cinco digitos
    @Id
    @Schema(description = "ID único da obra",example = "12345")
    private Long id;
//    o ano de inicio é uma String de 4 caracteres
    @Schema(description = "Ano de inicio da criação da obra",example = "2009")
    @Size(min = 4, max = 4, message = "O ano de inicio deve ter 4 caracteres")
    @Column(name = "ano_inicio")
    private String anoInicio;

//    o ano final é uma String de 4 caracteres
    @Schema(description = "Ano final da criação da obra",example = "2011")
    @Size(min = 4, max = 4, message = "O ano de final deve ter 4 caracteres")
    @Column(name = "ano_final")
    private String anoFinal;

    // a descrição da obra é uma String
    @Schema(description = "Descrição da obra",example = "Más Notícias é uma obra de arte feita por Rodolfo Amoedo em 1895. Retrata uma mulher sentada em uma poltrona, com o olhar direcionado para a frente, encarando quem a observa.")
    @Column(name = "desc_obra")
    private String descObra;

    // o nome da obra é uma string not null que deve ter no máximo 100
    @NotNull(message = "O nome da obra não pode ser nulo")
    @Size(max = 100,message = "O nome da obra deve ter no máximo 100 caracteres")
    @Schema(description = "Nome da obra",example = "Más Notícias")
    @Column(name = "nm_obra")
    private String nomeObra;

    // id do genero é um long de 5 caracteres que relaciona a obra com genero
    @Size(max=5,min =5,message = "O id genero deve ter 5 caracteres")
    @Schema(description = "Id que relaciona a obra com o genero",example = "12345")
    @Column(name = "id_genero")
    private Long idGenero;

    // id do artista é um long de 5 caracteres que relaciona a obra com genero
    @Size(max=5,min =5,message = "O id artista deve ter 5 caracteres")
    @Schema(description = "Id que relaciona a obra com o artista",example = "12345")
    @Column(name = "id_artista")
    private Long idArtista;

    // id do museu é um long de 5 caracteres que relaciona a obra com museu
    @Size(max=5,min =5,message = "O id museu deve ter 5 caracteres")
    @Schema(description = "Id que relaciona a obra com o museu",example = "12345")
    @Column(name = "id_museu")
    private Long idMuseu;

    // a url é o caminho da imagem no firebase
    @Size(max=500,message = "A URL da imagem deve ter no máximo 500 caracteres")
    @Schema(description = "URL da imagem",example = "https://firebasestorage.googleapis.com/v0/b/leontisfotos.appspot.com/o/usuarios%2Fuser.webp?alt=media&token=47824e40-be55-4657-a518-28528dcba3f7")
    @Column(name = "url_imagem")
    private String urlImagem;


}
