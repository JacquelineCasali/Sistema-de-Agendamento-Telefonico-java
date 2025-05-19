package com.contato.service;

import com.contato.domain.Contato;
import com.contato.dto.ContatoDTO;
import com.contato.infra.exceptions.ContatoNaoEncontradoException;
import com.contato.infra.exceptions.MultiplasRegrasException;
import com.contato.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public Contato salvar(ContatoDTO dto) {
// armazena varias mensagens de erro
        List<String> erros = new ArrayList<>();


        if (contatoRepository.existsByContatoNomeOrContatoEmail(
                dto.getContatoNome(),
                dto.getContatoEmail())) {
            erros.add("Já existe um contato com esse nome ou email");

        }
        if (contatoRepository.existsByContatoCelular(dto.getContatoCelular())) {
            erros.add("Número de celular já cadastrado");
        }


        String contatoCelular = dto.getContatoCelular().replaceAll("[^0-9]", "");
        if (contatoCelular.length() != 11) {
            erros.add("O número do celular deve conter 11 digitos!");

        }
        String contatoTelefone = dto.getContatoTelefone().replaceAll("[^0-9]", "");

        if (!contatoTelefone.isEmpty() && contatoTelefone.length() != 10) {
            erros.add("O número do telefone tem 10 dígitos!");
        }
        if (!erros.isEmpty()) {
            throw new MultiplasRegrasException(erros);
        }

        Contato contato = new Contato();
        contato.setContatoNome(dto.getContatoNome());
        contato.setContatoEmail(dto.getContatoEmail());
        contato.setContatoCelular(contatoCelular);
        contato.setContatoTelefone(dto.getContatoTelefone());
        contato.setContatoSnFavorito(dto.getContatoSnFavorito() != null ? dto.getContatoSnFavorito() : "N");
        contato.setContatoSnAtivo("S");
        contato.setContatoDhCad(LocalDateTime.now());
        return contatoRepository.save(contato);
    }

    public List<Contato> listarTodos() {
        return contatoRepository.findAll();
    }

    public Contato buscarPorId(Long id) {
        return contatoRepository.findById(id)
                .orElseThrow(() -> new ContatoNaoEncontradoException("Contato não encontrado!"));
    }


}
