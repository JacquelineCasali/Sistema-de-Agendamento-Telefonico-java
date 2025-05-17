package com.contato.service;

import com.contato.domain.Contato;
import com.contato.dto.ContatoDTO;
import com.contato.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public Contato salvar(ContatoDTO dto) {

        String contatoCelular = dto.getContatoCelular().replaceAll("[^0-9]", "");
        if (contatoCelular.length() < 11) {
            throw new RuntimeException("Celular deve ter 11 digitos!");

        }

        if (contatoRepository.existsByContatoNomeOrContatoEmail(
                dto.getContatoNome(),
                dto.getContatoEmail())) {
            throw new RuntimeException("Já existe um contato com esse nome ou email");
        } else if (contatoRepository.existsByContatoCelular(dto.getContatoCelular())) {
            throw new RuntimeException("Já existe um contato com esse celular");
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
                .orElseThrow(() -> new RuntimeException("Contato não encontrado!"));
    }



}
