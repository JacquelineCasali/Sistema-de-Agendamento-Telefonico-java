package com.contato.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContatoDTO {
    private String contatoNome;
    private String contatoEmail;
    private String contatoCelular;
    private String contatoTelefone;

}
