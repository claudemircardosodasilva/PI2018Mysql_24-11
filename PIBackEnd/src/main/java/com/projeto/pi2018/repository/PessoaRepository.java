package com.projeto.pi2018.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projeto.pi2018.model.Pessoa;
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	@Query(value="SELECT * FROM pessoa p WHERE p.cpf = :cpf",nativeQuery=true)
    public Pessoa buscarCpf(@Param("cpf") String cpf);
	
	@Query(value="SELECT * FROM pessoa p WHERE p.person_Id = :personId",nativeQuery=true)
    public Pessoa buscarPersonId(@Param("personId") String personId);

}
