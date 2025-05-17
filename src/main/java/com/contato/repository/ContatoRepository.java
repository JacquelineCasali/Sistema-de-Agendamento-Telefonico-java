package com.contato.repository;

import com.contato.domain.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

   boolean existsByContatoNomeOrContatoEmail(String contatoNome, String contatoEmail);
   boolean existsByContatoCelular(String contatoCelular);

}
