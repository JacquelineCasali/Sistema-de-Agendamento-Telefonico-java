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
    public ResponseEntity<Contato> criar (@RequestBody @Valid ContatoDTO dto){
        Contato contato =this.contatoService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(contato);
    }

    @GetMapping
    public ResponseEntity<List<Contato>> listarTodos() {
        List<Contato> contatos = contatoService.listarTodos();
        return ResponseEntity.ok(contatos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Contato> buscarContato(@PathVariable Long id) {
        return ResponseEntity.ok(contatoService.buscarPorId(id));
    }

}
