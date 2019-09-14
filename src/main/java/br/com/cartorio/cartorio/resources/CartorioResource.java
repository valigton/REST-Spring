package br.com.cartorio.cartorio.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartorio.cartorio.models.Cartorio;
import br.com.cartorio.cartorio.repositories.CartorioRepository;

@RestController
@RequestMapping("/cartorio")
public class CartorioResource {
	
	@Autowired
	private CartorioRepository cartorioRepo;
	
	@PostMapping
	public Cartorio adicionar(@Valid @RequestBody Cartorio cartorio) {
		return cartorioRepo.save(cartorio);
	}
	
	@GetMapping("/cartorios")
	public List<Cartorio> listar() {
		return cartorioRepo.findAll();
	}
		
	@GetMapping("/{id}")
	public ResponseEntity<Cartorio> buscar(@PathVariable Long id) {
		Cartorio existe = cartorioRepo.getOne(id);
		
		if (existe == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(existe);
	}
	
	@GetMapping("/endereco/{endereco}")
	public List<Cartorio> buscarPorEndereco(@PathVariable String endereco) {
		return cartorioRepo.procurar(endereco);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cartorio> atualizar(@PathVariable Long id, 
			@Valid @RequestBody Cartorio Cartorio) {
		Cartorio existente = cartorioRepo.getOne(id);
		
		if (existente == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(Cartorio, existente, "id");
		
		existente = cartorioRepo.save(existente);
		
		return ResponseEntity.ok(existente);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Cartorio Cartorio = cartorioRepo.getOne(id);
		
		if (Cartorio == null) {
			return ResponseEntity.notFound().build();
		}
		
		cartorioRepo.delete(Cartorio);
		
		return ResponseEntity.noContent().build();
	}
	
	
	
}
