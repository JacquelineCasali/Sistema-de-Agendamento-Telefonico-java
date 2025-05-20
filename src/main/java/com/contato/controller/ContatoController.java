package com.contato.controller;

import com.contato.domain.Contato;
import com.contato.dto.ContatoDTO;
import com.contato.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")

public class ContatoController {
    @Autowired
    private ContatoService contatoService;

    @PostMapping
    public ResponseEntity<Contato> criar(@RequestBody @Valid ContatoDTO dto) {
        Contato contato = this.contatoService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(contato);
    }

//    @GetMapping
//    public ResponseEntity<List<Contato>> listarTodos() {
//        List<Contato> contatos = contatoService.listarTodos();
//        return ResponseEntity.ok(contatos);
//    }

    @GetMapping("/{contatoId}")
    public ResponseEntity<Contato> buscarContato(@PathVariable Long contatoId) {
        return ResponseEntity.ok(contatoService.buscarPorId(contatoId));
    }
    @GetMapping
    public ResponseEntity<List<Contato>> filtrar(
            @RequestParam(required = false) String contatoNome,
            @RequestParam(required = false) String contatoCelular) {

        List<Contato> contatos = contatoService.filtrar(contatoNome, contatoCelular);
        return ResponseEntity.ok(contatos);
    }



    @PutMapping("/{contatoId}")
    public ResponseEntity<Contato> atualizarContato(@PathVariable Long contatoId, @RequestBody @Valid ContatoDTO dto) {

        Contato contatoAtualizado = this.contatoService.atualizarContato(contatoId, dto);
        return ResponseEntity.ok(contatoAtualizado);

    }
// favoritar e desfavoritar
    @PatchMapping("/{contatoId}/favorito")
    public ResponseEntity<Contato> favoritarDesfavoritar(@PathVariable Long contatoId) {
        Contato contatoFavorito = this.contatoService.alternarFavorito(contatoId);
        return ResponseEntity.ok(contatoFavorito);

    }

    @DeleteMapping("/{contatoId}")
    public ResponseEntity<Void> desativarContato(@PathVariable Long contatoId) {
        contatoService.desativarContato(contatoId);
        return ResponseEntity.noContent().build();

    }
}