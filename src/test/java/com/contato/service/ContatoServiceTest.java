package com.contato.service;
import com.contato.domain.Contato;
import com.contato.dto.ContatoDTO;
import com.contato.infra.exceptions.ContatoNaoEncontradoException;
import com.contato.infra.exceptions.MultiplasRegrasException;
import com.contato.repository.ContatoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ContatoServiceTest {
    @Mock
    private ContatoRepository contatoRepository;

    @InjectMocks
    private ContatoService contatoService;

    private ContatoDTO contatoDTO;
    private Contato contato;

    @BeforeEach
    void setup() {
        contatoDTO = new ContatoDTO();
        contatoDTO.setContatoNome("João");
        contatoDTO.setContatoEmail("joao@email.com");
        contatoDTO.setContatoCelular("11912345678");
        contato = new Contato();
        contato.setContatoNome("João");
        contato.setContatoEmail("joao@email.com");
        contato.setContatoCelular("11912345678");

    }
    @Test
    void createValidContato() {
        contatoDTO = new ContatoDTO();
        contatoDTO.setContatoNome("João");
        contatoDTO.setContatoEmail("joao@email.com");
        contatoDTO.setContatoCelular("11912345678");
        contatoDTO.setContatoTelefone("1133445566"); // <-- ADICIONE ESTA LINHA

        when(contatoRepository.save(any(Contato.class))).thenReturn(contato);

        Contato salvo = contatoService.salvar(contatoDTO);

        assertNotNull(salvo);
        verify(contatoRepository).save(any(Contato.class));
    }


    @Test
    void deveLancarExcecaoAoAtualizarContatoInexistente() {
        when(contatoRepository.findById(99L)).thenReturn(Optional.empty());

        ContatoNaoEncontradoException ex = assertThrows(ContatoNaoEncontradoException.class, () ->
                contatoService.atualizarContato(99L, contatoDTO)
        );

        assertTrue(ex.getMessage().contains("Contato não encontrado"));
    }



    @Test
    void deveBuscarContatoPorId() {
        when(contatoRepository.findById(1L)).thenReturn(Optional.of(contato));

        Contato resultado = contatoService.buscarPorId(1L);

        assertEquals("João", resultado.getContatoNome()); // <-- Agora passa
    }


    @Test
    void deveAtualizarContatoExistente() {
        contatoDTO.setContatoTelefone("1133445566");

        when(contatoRepository.findById(1L)).thenReturn(Optional.of(contato));
        when(contatoRepository.save(any(Contato.class))).thenReturn(contato);

        Contato atualizado = contatoService.atualizarContato(1L, contatoDTO);

        assertNotNull(atualizado);
        assertEquals("João", atualizado.getContatoNome());
        verify(contatoRepository).save(any(Contato.class));
    }


    @Test
    void deveLancarExcecaoAoDeletarContatoInexistente() {
        when(contatoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ContatoNaoEncontradoException.class, () ->
                contatoService.desativarContato(99L)
        );
    }
}



