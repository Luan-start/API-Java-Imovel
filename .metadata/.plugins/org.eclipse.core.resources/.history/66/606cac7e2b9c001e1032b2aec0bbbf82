package com.example.imovel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/imoveis")
public class ImovelController {
	
	@Autowired
	ImovelRepository repo;
	
	@GetMapping
	public ResponseEntity<List<Imovel>> getImoveis() {
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Imovel> inserirImovel(@RequestBody Imovel imovel) {
		Imovel ct = repo.save(imovel);
		return ResponseEntity.status(HttpStatus.CREATED).body(ct);
	}
	
	@PutMapping("/{idimovel}")
	public ResponseEntity<Imovel> alterarImovel(@PathVariable("idimovel") Long idimovel,
			@RequestBody Imovel imovel) {
		Optional<Imovel> opImovel = repo.findById(idimovel);
		try {
			Imovel ct = opImovel.get();
			ct.setRua(imovel.getRua());
			ct.setNumero(imovel.getNumero());
			ct.setBairro(imovel.getBairro());
			ct.setCidade(imovel.getCidade());
			ct.setEstado(imovel.getEstado());
			ct.setStatus(imovel.getStatus());
			repo.save(ct);
			return ResponseEntity.status(HttpStatus.OK).body(imovel);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Imovel> getUmImovel(@PathVariable("id") long id) {
		Optional<Imovel> opImovel = repo.findById(id);
		try {
			Imovel ct = opImovel.get();
			return ResponseEntity.status(HttpStatus.OK).body(ct);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Imovel> excluirUmImovel(@PathVariable("id") long id) {
		Optional<Imovel> opImovel = repo.findById(id);
		try {
			Imovel ct = opImovel.get();
			repo.delete(ct);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}