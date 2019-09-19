package com.challenge.sociotorcedor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.challenge.sociotorcedor.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	@Query("SELECT cliente FROM Cliente cliente LEFT JOIN FETCH cliente.campanhas WHERE UPPER(cliente.email) = UPPER(:email)")
    Cliente buscarPorEmail(@Param("email") String email);
	
}
