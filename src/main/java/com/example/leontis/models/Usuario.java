package com.example.leontis.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
private String nm_usuario;
@NotNull(message = "O sobrenome não pode ser nulo")
@Size(min = 3, message = "O sobrenome deve ter pelo menos 3 carcteres")
@Size(max = 100, message = "O sobrenome deve ter menos de 100 carcteres")
private String sobrenome;
@NotNull(message = "O email não pode ser nulo")
@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
        flags = Pattern.Flag.CASE_INSENSITIVE)
private String email_usuario;
@Size(min = 9, max = 20, message = "O telefone deve ter 11 caracteres")
private String nr_tel_usuario;
@NotNull(message = "A data de nascimento não pode ser nula")
@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "A data deve estar no formato AAAA-MM-DD")
private String dt_nasci_usuario;
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
private String senha_usuario;

    public Usuario() {
    }

    public Usuario(String id, String nm_usuario, String sobrenome, String email_usuario, String nr_tel_usuario, String  dt_nasci_usuario, String biografia, String sexo, String apelido, String senha_usuario) {
        this.id = id;
        this.nm_usuario = nm_usuario;
        this.sobrenome = sobrenome;
        this.email_usuario = email_usuario;
        this.nr_tel_usuario = nr_tel_usuario;
        this.dt_nasci_usuario = dt_nasci_usuario;
        this.biografia = biografia;
        this.sexo = sexo;
        this.apelido = apelido;
        this.senha_usuario = senha_usuario;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getDt_nasci_usuario() {
        return dt_nasci_usuario;
    }

    public void setDt_nasci_usuario(String dt_nasci_usuario) {
        this.dt_nasci_usuario = dt_nasci_usuario;
    }

    public String getNr_tel_usuario() {
        return nr_tel_usuario;
    }

    public void setNr_tel_usuario(String nr_tel_usuario) {
        this.nr_tel_usuario = nr_tel_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getNm_usuario() {
        return nm_usuario;
    }

    public void setNm_usuario(String nm_usuario) {
        this.nm_usuario = nm_usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nm_usuario='" + nm_usuario + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email_usuario='" + email_usuario + '\'' +
                ", nr_tel_usuario='" + nr_tel_usuario + '\'' +
                ", dt_nasci_usuario=" + dt_nasci_usuario +
                ", biografia='" + biografia + '\'' +
                ", sexo='" + sexo + '\'' +
                ", apelido='" + apelido + '\'' +
                ", senha_usuario='" + senha_usuario + '\'' +
                '}';
    }
}
