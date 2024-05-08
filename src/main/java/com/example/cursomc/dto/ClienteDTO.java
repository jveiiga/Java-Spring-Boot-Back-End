package com.example.cursomc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.services.validation.ClienteUpdate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@ClienteUpdate
public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message="Preenchimento obrigatório!")
    @Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres!")
    private String nome;

    @NotEmpty(message="Preenchimento obrigatório!")
    @Email(message="E-mail inválido!")
    private String email;


    public ClienteDTO() {
    }

    public ClienteDTO(Cliente obj) {
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }    
}
