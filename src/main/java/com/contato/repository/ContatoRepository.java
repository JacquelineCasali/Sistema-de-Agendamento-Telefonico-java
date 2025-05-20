package com.contato.repository;

import com.contato.domain.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

   boolean existsByContatoNomeOrContatoEmail(String contatoNome, String contatoEmail);
   boolean existsByContatoCelular(String contatoCelular);
   boolean existsByContatoCelularAndContatoIdNot(String contatoCelular, Long contatoId);
   List<Contato> findByContatoSnAtivo(String contatoSnAtivo);

   boolean existsByContatoNomeAndContatoIdNot(String contatoNome, Long contatoId);
   boolean existsByContatoEmailAndContatoIdNot(String contatoEmail, Long contatoId);

//    A filtros por Nome e contatoCelular
//@Query("SELECT c FROM Contato c WHERE " +
//        "(:contatoNome IS NULL OR LOWER(c.contatoNome) LIKE LOWER(CONCAT('%', :contatoNome, '%'))) AND " +
//        "(:contatoCelular IS NULL OR c.contatoCelular LIKE %:contatoCelular%) AND" + "c.contatoSnAtivo = 'S'")
//List<Contato> filtrar(@Param("contatoNome") String contatoNome, @Param("contatoCelular") String contatoCelular);

   @Query("SELECT c FROM Contato c WHERE " +
           "c.contatoSnAtivo = 'S' AND " +
             "(:contatoNome IS NULL OR LOWER(c.contatoNome) LIKE :contatoNome) " +
           "AND (:contatoCelular IS NULL OR c.contatoCelular LIKE :contatoCelular)")
   List<Contato> filtrar(
           @Param("contatoNome") String contatoNome,
           @Param("contatoCelular") String contatoCelular);
}
