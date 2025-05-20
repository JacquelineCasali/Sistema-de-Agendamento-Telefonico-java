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

        String contatoCelular = dto.getContatoCelular().replaceAll("[^0-9]", "");
        if (contatoCelular.length() != 11) {
            erros.add("O número do celular deve conter 11 digitos!");

        }
        String contatoTelefone = dto.getContatoTelefone().replaceAll("[^0-9]", "");
        if (!contatoTelefone.isEmpty() && contatoTelefone.length() != 10) {
            erros.add("O número do telefone tem 10 dígitos!");
        }

        if (contatoRepository.existsByContatoNomeOrContatoEmail(
                dto.getContatoNome(),
                dto.getContatoEmail())) {
            erros.add("Já existe um contato com esse nome ou email");

        }
        if (contatoRepository.existsByContatoCelular(contatoCelular)) {
            erros.add("Número de celular já cadastrado");
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

//    public List<Contato> listarTodos() {
//        return contatoRepository.findByContatoSnAtivo("S");
//    }

    public Contato buscarPorId(Long contatoId) {
        return contatoRepository.findById(contatoId)
                .orElseThrow(() -> new ContatoNaoEncontradoException("Contato não encontrado!"));
    }
    public List<Contato> filtrar(String contatoNome, String contatoCelular) {
        String nome = (contatoNome == null || contatoNome.isBlank()) ? null : "%" + contatoNome.toLowerCase() + "%";
        String celular = (contatoCelular == null || contatoCelular.isBlank()) ? null : "%" + contatoCelular + "%";
        List<Contato> contatos = contatoRepository.filtrar(nome, celular);
if(contatos.isEmpty()){
    throw new ContatoNaoEncontradoException("Contato não encontrado!");
}
        return contatos;
    }
    public Contato atualizarContato(Long contatoId, ContatoDTO dto) {
        Contato contato = contatoRepository.findById(contatoId).orElseThrow(() -> new ContatoNaoEncontradoException("Contato não encontrado com o ID: " + contatoId));
        List<String> erros = new ArrayList<>();
        String contatoCelular = dto.getContatoCelular().replaceAll("[^0-9]", "");
        if (contatoCelular.length() != 11) {
            erros.add("O número do celular deve conter 11 digitos!");

        }
        String contatoTelefone = dto.getContatoTelefone().replaceAll("[^0-9]", "");
        if (!contatoTelefone.isEmpty() && contatoTelefone.length() != 10) {
            erros.add("O número do telefone tem 10 dígitos!");
        }


        if (contatoRepository.existsByContatoNomeAndContatoIdNot(dto.getContatoNome(), contatoId)) {
            erros.add("Já existe um contato com esse nome");
        }

        if (contatoRepository.existsByContatoEmailAndContatoIdNot(dto.getContatoEmail(), contatoId)) {
            erros.add("Já existe um contato com esse email");
        }

        if (contatoRepository.existsByContatoCelularAndContatoIdNot(contatoCelular, contatoId)) {
            erros.add("Número de celular já cadastrado");
        }


        if (!erros.isEmpty()) {
            throw new MultiplasRegrasException(erros);
        }

        contato.setContatoNome(dto.getContatoNome());
        contato.setContatoEmail(dto.getContatoEmail());
        contato.setContatoCelular(contatoCelular);
        contato.setContatoTelefone(dto.getContatoTelefone());
        contato.setContatoSnFavorito(dto.getContatoSnFavorito() != null ? dto.getContatoSnFavorito() : "N");
        contato.setContatoSnAtivo("S");
        contato.setContatoDhCad(LocalDateTime.now());
        return contatoRepository.save(contato);
    }
// favoritar e desfavoritar
public Contato alternarFavorito(Long contatoId) {
    Contato contato = contatoRepository.findById(contatoId)
            .orElseThrow(() -> new ContatoNaoEncontradoException("Contato não encontrado."));

    String status = contato.getContatoSnFavorito();
    contato.setContatoSnFavorito("S".equals(status) ? "N" : "S");
    return contatoRepository.save(contato);
}
    public void desativarContato(Long contatoId) {
        Contato contato = contatoRepository.findById(contatoId)
                .orElseThrow(() -> new ContatoNaoEncontradoException("Contato com ID " + contatoId + " não encontrado."));

        contato.setContatoSnAtivo("N");
        contatoRepository.save(contato);
    }
}
