package com.example.imovel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.imovel.entities.Imovel;
import com.example.imovel.repository.ImovelRepository;

@CrossOrigin
@RestController
@RequestMapping("/imoveis") //Caminho base para todas as operações 
public class ImovelController {
	
	@Autowired
	ImovelRepository repo;
	// Endpoint para obter todos os imóveis
	@GetMapping()
	public ResponseEntity<List<Imovel>> getImoveis() {
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
	}
	// Endpoint para inserir um novo imóvel
	@PostMapping()
	public ResponseEntity<Imovel> inserirImovel(@RequestBody Imovel imovel) {
		Imovel im = repo.save(imovel);
		return ResponseEntity.status(HttpStatus.CREATED).body(im);
	}
	// Endpoint para alterar um imóvel existente
	@PutMapping("/{idimovel}")
	public ResponseEntity<Imovel> alterarImovel(@PathVariable("idimovel") Long idimovel,
			@RequestBody Imovel imovel) {
		Optional<Imovel> opImovel = repo.findById(idimovel);
		try {
			Imovel im = opImovel.get();
			im.setRua(imovel.getRua());
			im.setNumero(imovel.getNumero());
			im.setBairro(imovel.getBairro());
			im.setCidade(imovel.getCidade());
			im.setEstado(imovel.getEstado());
			im.setStatus(imovel.getStatus());
			repo.save(im);
			return ResponseEntity.status(HttpStatus.OK).body(im);	
		}
		catch (Exception e) {
			// Retorna NOT FOUND se o imóvel não for encontrado
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	// Endpoint para obter informações sobre um imóvel específico
	@GetMapping("/{id}")
	public ResponseEntity<Imovel> getUmImovel(@PathVariable("id") long id) {
		Optional<Imovel> opImovel = repo.findById(id);
		try {
			Imovel im = opImovel.get();
			return ResponseEntity.status(HttpStatus.OK).body(im);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	// Endpoint para excluir um imóvel específico
	@DeleteMapping("/{id}")
	public ResponseEntity<Imovel> excluirUmImovel(@PathVariable("id") long id) {
		Optional<Imovel> opImovel = repo.findById(id);
		try {
			Imovel im = opImovel.get();
			repo.delete(im);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}