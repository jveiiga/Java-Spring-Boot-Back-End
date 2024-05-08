package com.example.cursomc.repositories;

import com.example.cursomc.domain.Cliente;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

// Método contruido com o padrão de nomes do framework
// Automaticamente o framework cria a consulta JPQL
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Transactional() //Nas versões atuais não é mais necessário colocar readOnly=true no parâmetro
    Cliente findByEmail(String email);
}
