package com.contato.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contato_id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(unique = true,  length = 100)
    private String contato_nome;

    @Email
    @Column(unique = true, length = 255)
    private String contato_email ;

    @NotBlank(message = "Email é obrigatório")
    @Column(unique = true, nullable = false,length = 11)

    private String contato_celular ;
    @Column(length = 10)
    private String contato_telefone ;
    @Column(length = 1)
    private String contato_sn_favorito ="N" ;
    @Column(length = 1)
    private String contato_sn_ativo ="S" ;
    @Column
    private LocalDateTime contatoDhCad = LocalDateTime.now();

    public Long getContato_id() {
        return contato_id;
    }

    public void setContato_id(Long contato_id) {
        this.contato_id = contato_id;
    }

    public String getContato_nome() {
        return contato_nome;
    }

    public void setContato_nome(String contato_nome) {
        this.contato_nome = contato_nome;
    }

    public String getContato_email() {
        return contato_email;
    }

    public void setContato_email(String contato_email) {
        this.contato_email = contato_email;
    }

    public String getContato_celular() {
        return contato_celular;
    }

    public void setContato_celular(String contato_celular) {
        this.contato_celular = contato_celular;
    }

    public String getContato_telefone() {
        return contato_telefone;
    }

    public void setContato_telefone(String contato_telefone) {
        this.contato_telefone = contato_telefone;
    }

    public String getContato_sn_favorito() {
        return contato_sn_favorito;
    }

    public void setContato_sn_favorito(String contato_sn_favorito) {
        this.contato_sn_favorito = contato_sn_favorito;
    }

    public String getContato_sn_ativo() {
        return contato_sn_ativo;
    }

    public void setContato_sn_ativo(String contato_sn_ativo) {
        this.contato_sn_ativo = contato_sn_ativo;
    }

    public LocalDateTime getContatoDhCad() {
        return contatoDhCad;
    }

    public void setContatoDhCad(LocalDateTime contatoDhCad) {
        this.contatoDhCad = contatoDhCad;
    }
}
