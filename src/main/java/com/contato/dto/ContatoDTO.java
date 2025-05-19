package com.contato.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContatoDTO {
    private Long contatoId;
    @NotBlank(message = "Nome é obrigatório")
    private String contatoNome;
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email precisa está em formato de email")
    private String contatoEmail;

    @NotBlank(message = "Número de Celular é obrigatório")
    private String contatoCelular;
   private String contatoTelefone;
    private String contatoSnFavorito  ;



}
