package com.example.leontis.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
public class Usuario {
@Id
private String id;
@NotNull(message = "O nome não pode ser nulo")
@Size(min = 3, message = "O nome deve ter pelo menos 3 carcteres")
@Size(max = 100, message = "O nome deve ter menos de 100 carcteres")
@Column(name = "nm_usuario")
private String nome;
@NotNull(message = "O sobrenome não pode ser nulo")
@Size(min = 3, message = "O sobrenome deve ter pelo menos 3 carcteres")
@Size(max = 100, message = "O sobrenome deve ter menos de 100 carcteres")
private String sobrenome;
@NotNull(message = "O email não pode ser nulo")
@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
        flags = Pattern.Flag.CASE_INSENSITIVE)
@Column(name = "email_usuario")
private String email;
@Size(min = 9, max = 20, message = "O telefone deve ter 11 caracteres")
@Column(name = "nt_tel_usuatio")
private String telefone;
@NotNull(message = "A data de nascimento não pode ser nula")
@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "A data deve estar no formato AAAA-MM-DD")
@Column(name = "dt_nasci_usuario")
private String dataNascimento;
@Size(min = 10, message = "A biografia deve ter pelo menos 10 carcteres")
@Size(max = 100, message = "A biografia deve ter menos de 100 carcteres")
private String biografia;
@NotNull(message = "O sexo não pode ser nulo")
private String sexo;
    @Size(min = 3, message = "A biografia deve ter pelo menos 3 carcteres")
    @Size(max = 100, message = "A biografia deve ter menos de 100 carcteres")
private String apelido;
@NotNull(message = "A senha não pode ser nulo")
@Size(min = 3, message = "A senha deve ter pelo menos 3 carcteres")
@Size(max = 100, message = "A senha deve ter menos de 100 carcteres")
@Column(name = "senha_usuario")
private String senha;

    public Usuario() {
    }

    public Usuario(String id, String nome, String sobrenome, String email, String telefone, String dataNascimento, String biografia, String sexo, String apelido, String senha) {
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
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
                '}';
    }
}
