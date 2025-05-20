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
    @Column(name = "contato_id")
    private Long contatoId;

    @NotBlank(message = "Nome é obrigatório")
    @Column(name = "contato_nome", unique = true, length = 100)
    private String contatoNome;

    @Email(message = "Email precisa está em formato de email")

    @Column(name = "contato_email", unique = true, length = 255)
    private String contatoEmail;

    @NotBlank(message = "Número de Celular é obrigatório")
    @Column(name = "contato_celular", unique = true, nullable = false, length = 11)
    private String contatoCelular;
    @Column(name="contato_telefone",length = 10)
    private String contatoTelefone;
    @Column(name = "contato_sn_favorito",length = 1)
    private String contatoSnFavorito = "N";
    @Column(name="contato_sn_ativo",length = 1)
    private String contatoSnAtivo = "S";
    @Column
    private LocalDateTime contatoDhCad = LocalDateTime.now();



}
