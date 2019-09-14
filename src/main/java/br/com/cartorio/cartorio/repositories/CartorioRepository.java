package br.com.cartorio.cartorio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cartorio.cartorio.models.Cartorio;

public interface CartorioRepository extends JpaRepository<Cartorio, Long>{

	Cartorio findByNome(String nome);
	
	//JPQL 
	@Query("SELECT c FROM Cartorio c WHERE c.endereco LIKE :endereco%")
	List<Cartorio> procurar(@Param("endereco") String endereco);
}
