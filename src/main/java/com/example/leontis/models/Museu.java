package com.example.leontis.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Museu {

    @Id
    @Schema(description = "Id do museu",example = "12345")
    private Long id;

    @Column(name = "nm_museu")
    @NotNull(message = "O nome não pode ser nulo")
    @Size(min = 3, message = "O nome deve ter pelo menos 3 carcteres")
    @Size(max = 100, message = "O nome deve ter menos de 100 carcteres")
    @Schema(description = "Nome do museu",example = "Museu de Arte Contemporânea de Niterói (MAC)")
    private String nomeMuseu;

    @Column(name = "desc_museu")
    @Schema(description = "Introdução do museu",example = "A forma futurista criada por Niemeyer tornou-se um marco da arquitetura moderna mundial, sendo considerada uma das sete maravilhas do Mundo em museus pela mídia especializada.")
    private String descMuseu;

    @Column(name = "id_endereco")
    @Schema(description = "Id do endereço",example = "12345")
    @Digits(integer = 5, fraction = 0)
    @NotNull(message = "O id_endereco não pode ser nulo")
    private Long idEndereco;

    @Column(name = "dt_inauguracao")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "A data deve estar no formato AAAA-MM-DD")
    @Schema(description = "Data de inauguração do museu",example = "2007-09-12")
    private String dtInaguracao;

    @Column(name = "nr_tel_museu")
    @Size(min = 9, max = 20, message = "O telefone deve ter 11 caracteres")
    @NotNull(message = "O telefone não pode ser nulo")
    @Schema(description = "Telefone do museu",example = "(11)95755-0383")
    private String TelefoneMuseu;

    @Column(name = "url_imagem")
    @Size(max=500,message = "A URL da imagem deve ter no máximo 500 caracteres")
    @Schema(description = "URL da imagem do usuário",example = "https://firebasestorage.googleapis.com/v0/b/leontisfotos.appspot.com/o/usuarios%2Fuser.webp?alt=media&token=47824e40-be55-4657-a518-28528dcba3f7")
    private String urlImagem;

}
