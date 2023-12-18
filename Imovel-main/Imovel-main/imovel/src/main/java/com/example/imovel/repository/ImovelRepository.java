package com.example.imovel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.imovel.entities.Imovel;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long> {

}
//Representa uma interface de repositório para a entidade Imovel em uma 
//aplicação Spring Boot, Facilitar a interação com o banco de dados,
//Facilitar a interação com o banco de dados