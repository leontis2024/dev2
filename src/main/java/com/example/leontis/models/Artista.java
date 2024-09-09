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
    @Schema(description = "Nome do artista",example = "Cândido Portinari")
    private String nomeArtista;

    @Column(name = "dt_nasc_artista")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "A data deve estar no formato AAAA-MM-DD")
    @Schema(description = "Data de nascimento do artista",example = "1903-12-29")
    private String dtNascArtista;

    @Column(name = "dt_falecimento")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "A data deve estar no formato AAAA-MM-DD")
    @Schema(description = "Data de falecimento do artista",example = "1962-02-06")
    private String dtFalecimento;

    @Column(name = "local_nasc")
    @Size(max = 150, message = "O local de nascimento do atista deve ter no máximo 150 caracteres")
    @Schema(description = "Local de nascimento do artista",example = "Brodowski, São Paulo")
    private String localNasc;

    @Column(name = "local_morte")
    @Size(max = 150, message = "O local de morte do atista deve ter no máximo 150 caracteres")
    @Schema(description = "Local de nascimento do artista",example = "Rio de Janeiro, Rio de Janeiro")
    private String localMorte;

    @Column(name = "desc_artista")
    @Schema(description = "Descrição do artista",example = "Candido Portinari OMC foi um artista plástico brasileiro. Portinari pintou mais de cinco mil obras, de pequenos esboços e pinturas de proporções padrão, como O Lavrador de Café, até gigantescos murais, como os painéis Guerra e Paz, presenteados à sede da ONU em Nova Iorque em 1956, e que, em dezembro de 2010, graças aos esforços de seu filho, retornaram para exibição no Teatro Municipal do Rio de Janeiro.")
    private String descArtista;

    @Column(name = "url_imagem")
    @Size(max=500,message = "A URL da imagem deve ter no máximo 500 caracteres")
    @Schema(description = "URL da imagem do artista",example = "https://firebasestorage.googleapis.com/v0/b/leontisfotos.appspot.com/o/usuarios%2Fuser.webp?alt=media&token=47824e40-be55-4657-a518-28528dcba3f7")
    private String urlImagem;


}
