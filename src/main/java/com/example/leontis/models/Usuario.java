package com.example.leontis.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;
//Entidade da tabela usuário
@Entity
public class Usuario {
//    Id do usuário é uma long de cinco digitos
@Id
@Schema(description = "ID único do usuário",example = "12345")
private Long id;

// o nome do usuário é uma string not null que deve ter no minimo tres caracteres e no máximo 100
@NotNull(message = "O nome não pode ser nulo")
@Size(min = 3, message = "O nome deve ter pelo menos 3 carcteres")
@Size(max = 100, message = "O nome deve ter menos de 100 carcteres")
@Column(name = "nm_usuario")
@Schema(description = "Nome do usuário",example = "Ana")
private String nome;

//   o sobrenome do usuário é uma string not null que deve ter no minimo tres caracteres e no máximo 100
@NotNull(message = "O sobrenome não pode ser nulo")
@Size(min = 3, message = "O sobrenome deve ter pelo menos 3 carcteres")
@Size(max = 100, message = "O sobrenome deve ter menos de 100 carcteres")
@Schema(description = "Sobrenome do usuário",example = "Romera")
private String sobrenome;

// o email é uma string not null que deve estar no formato email@email.com e não pode ser nula
@NotNull(message = "O email não pode ser nulo")
@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
        flags = Pattern.Flag.CASE_INSENSITIVE)
@Column(name = "email_usuario")
@Schema(description = "Email do usuário",example = "anaromera@gmail.com")
private String email;

// o número de telefone é uma string not null de no minimo 9 caracteres e no maximo 20
@Size(min = 9, max = 20, message = "O telefone deve ter 11 caracteres")
@NotNull(message = "O telefone não pode ser nulo")
@Column(name = "nr_tel_usuario")
@Schema(description = "Telefone do usuário",example = "(11)95755-0383")
private String telefone;

// a data de nascimento é uma string que deve estar no forma aaaa-mm-dd
@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "A data deve estar no formato AAAA-MM-DD")
@Column(name = "dt_nasci_usuario")
@Schema(description = "Data de nascimento do usuário",example = "2007-09-12")
private String dataNascimento;

// a biografia é uma string que deve ter pelo menos 10 caracteres e no maximo 100
@Size(min = 10, message = "A biografia deve ter pelo menos 10 carcteres")
@Size(max = 100, message = "A biografia deve ter menos de 100 carcteres")
@Schema(description = "Biografia do usuário",example = "Oi, meu nome é Ana Bia e estou usando o Leontis!")
private String biografia;

// o sexo é uma string not null
@NotNull(message = "O sexo não pode ser nulo")
@Schema(description = "Genero do usuário",example = "Feminino")
private String sexo;

// o apelido é uma string de no minimo 3 caracteres e no maximo 100
@Size(min = 3, message = "O apelido deve ter pelo menos 3 carcteres")
@Size(max = 100, message = "O apelido deve ter menos de 100 carcteres")
@Schema(description = "Apelido do usuário",example = "Bia")
private String apelido;

// a senha é uma string not null que deve ter no minimo 3 caracteres e no maximo 100
@NotNull(message = "A senha não pode ser nulo")
@Size(min = 5, message = "A senha deve ter pelo menos 3 carcteres")
@Size(max = 100, message = "A senha deve ter menos de 100 carcteres")
@Column(name = "senha_usuario")
@Schema(description = "Senha da conta do usuário",example = "1234Senha")
private String senha;
// a url é o caminho da imagem no firebase
@Column(name = "url_imagem")
@Schema(description = "URL da imagem do usuário",example = "https://firebasestorage.googleapis.com/v0/b/leontisfotos.appspot.com/o/%s?alt=media")
private String urlImagem;

// Construtor vazio
    public Usuario() {
    }

// Construtor com todos os atributos
    public Usuario(Long id, String nome, String sobrenome, String email, String telefone, String dataNascimento, String biografia, String sexo, String apelido, String senha, String urlImagem) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.biografia = biografia;
        this.sexo = sexo;
        this.apelido = apelido;
        this.senha = senha;
        this.urlImagem = urlImagem;
    }

    //    Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "O nome não pode ser nulo") @Size(min = 3, message = "O nome deve ter pelo menos 3 carcteres") @Size(max = 100, message = "O nome deve ter menos de 100 carcteres") String getNome() {
        return nome;
    }

    public void setNome(@NotNull(message = "O nome não pode ser nulo") @Size(min = 3, message = "O nome deve ter pelo menos 3 carcteres") @Size(max = 100, message = "O nome deve ter menos de 100 carcteres") String nome) {
        this.nome = nome;
    }

    public @NotNull(message = "O sobrenome não pode ser nulo") @Size(min = 3, message = "O sobrenome deve ter pelo menos 3 carcteres") @Size(max = 100, message = "O sobrenome deve ter menos de 100 carcteres") String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(@NotNull(message = "O sobrenome não pode ser nulo") @Size(min = 3, message = "O sobrenome deve ter pelo menos 3 carcteres") @Size(max = 100, message = "O sobrenome deve ter menos de 100 carcteres") String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public @NotNull(message = "O email não pode ser nulo") @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE) String getEmail() {
        return email;
    }

    public void setEmail(@NotNull(message = "O email não pode ser nulo") @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE) String email) {
        this.email = email;
    }

    public @Size(min = 9, max = 20, message = "O telefone deve ter 11 caracteres") String getTelefone() {
        return telefone;
    }

    public void setTelefone(@Size(min = 9, max = 20, message = "O telefone deve ter 11 caracteres") String telefone) {
        this.telefone = telefone;
    }

    public @NotNull(message = "A data de nascimento não pode ser nula") @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "A data deve estar no formato AAAA-MM-DD") String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(@NotNull(message = "A data de nascimento não pode ser nula") @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "A data deve estar no formato AAAA-MM-DD") String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public @Size(min = 10, message = "A biografia deve ter pelo menos 10 carcteres") @Size(max = 100, message = "A biografia deve ter menos de 100 carcteres") String getBiografia() {
        return biografia;
    }

    public void setBiografia(@Size(min = 10, message = "A biografia deve ter pelo menos 10 carcteres") @Size(max = 100, message = "A biografia deve ter menos de 100 carcteres") String biografia) {
        this.biografia = biografia;
    }

    public @NotNull(message = "O sexo não pode ser nulo") String getSexo() {
        return sexo;
    }

    public void setSexo(@NotNull(message = "O sexo não pode ser nulo") String sexo) {
        this.sexo = sexo;
    }

    public @Size(min = 3, message = "A biografia deve ter pelo menos 3 carcteres") @Size(max = 100, message = "A biografia deve ter menos de 100 carcteres") String getApelido() {
        return apelido;
    }

    public void setApelido(@Size(min = 3, message = "A biografia deve ter pelo menos 3 carcteres") @Size(max = 100, message = "A biografia deve ter menos de 100 carcteres") String apelido) {
        this.apelido = apelido;
    }

    public @NotNull(message = "A senha não pode ser nulo") @Size(min = 3, message = "A senha deve ter pelo menos 3 carcteres") @Size(max = 100, message = "A senha deve ter menos de 100 carcteres") String getSenha() {
        return senha;
    }

    public void setSenha(@NotNull(message = "A senha não pode ser nulo") @Size(min = 3, message = "A senha deve ter pelo menos 3 carcteres") @Size(max = 100, message = "A senha deve ter menos de 100 carcteres") String senha) {
        this.senha = senha;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    //    To string com todos os atributos

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", biografia='" + biografia + '\'' +
                ", sexo='" + sexo + '\'' +
                ", apelido='" + apelido + '\'' +
                ", senha='" + senha + '\'' +
                ", urlImagem='" + urlImagem + '\'' +
                '}';
    }
}
