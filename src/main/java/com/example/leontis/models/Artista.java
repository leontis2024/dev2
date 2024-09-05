package com.example.leontis.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Artista {
    @Id
    @Schema(description = "ID único do artista",example = "12345")
    private int id;

    @Size(max = 250, message = "O nome do artista deve ter no máximo 100 caracteres")
    @Column(name = "nm_artista")
    @Schema(description = "Nome do artista",example = "Ana")
    private String nomeArtista;

    @Column(name = "dt_nasc_artista")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "A data deve estar no formato AAAA-MM-DD")
    @Schema(description = "Data de nascimento do artista",example = "2007-09-12")
    private String dtNascArtista;

    @Column(name = "dt_falecimento")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "A data deve estar no formato AAAA-MM-DD")
    @Schema(description = "Data de falecimento do artista",example = "2007-09-12")
    private String dtFalecimento;

    @Column(name = "local_nasc")
    @Size(max = 150, message = "O local de nascimento do atista deve ter no máximo 150 caracteres")
    @Schema(description = "Local de nascimento do artista",example = "Roma")
    private String localNasc;

    @Column(name = "local_morte")
    @Size(max = 150, message = "O local de morte do atista deve ter no máximo 150 caracteres")
    @Schema(description = "Local de nascimento do artista",example = "São Paulo")
    private String localMorte;

    @Column(name = "desc_artista")
    @Schema(description = "Descrição do artista",example = "Más Notícias é uma obra de arte feita por Rodolfo Amoedo em 1895. Retrata uma mulher sentada em uma poltrona, com o olhar direcionado para a frente, encarando quem a observa.")
    private String descArtista;

    @Column(name = "url_imagem")
    @Size(max=500,message = "A URL da imagem deve ter no máximo 500 caracteres")
    @Schema(description = "URL da imagem do artista",example = "https://firebasestorage.googleapis.com/v0/b/leontisfotos.appspot.com/o/usuarios%2Fuser.webp?alt=media&token=47824e40-be55-4657-a518-28528dcba3f7")
    private String urlImagem;


}
