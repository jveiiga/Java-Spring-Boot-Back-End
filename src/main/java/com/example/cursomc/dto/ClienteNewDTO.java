package com.example.cursomc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.example.cursomc.services.validation.ClienteInsert;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@ClienteInsert
public class ClienteNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    // Cliente   
    @NotEmpty(message="Preenchimento obrigatório!")
    @Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres!"   ) 
    private String nome;

    @NotEmpty(message="Preenchimento do email obrigatório!")
    @Email(message="E-mail inválido!")
    private String email;

    @NotEmpty(message="Preenchimento do CPF obrigatório!")
    private String cpfOuCnpj;

    @NotEmpty
    private String senha;

    private Integer tipo;

    // Endereço
    @NotEmpty(message="Preenchimento da rua obrigatório!")
    private String logradouro;
    
    @NotEmpty(message="Preenchimento do número obrigatório!")
    private String numero;
    private String complemento;
    private String bairro;

    @NotEmpty(message="Preenchimento do CEP obrigatório!")
    private String cep;

    // Telefone
    @NotEmpty(message="Preenchimento do telefone obrigatório!")
    private String telefone1;
    private String telefone2;
    private String telefone3;

    // Cidade
    private Integer cidadeId;


    public ClienteNewDTO() {
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

    public String getCpfOuCnpj() {
        return this.cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public Integer getTipo() {
        return this.tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone1() {
        return this.telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return this.telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTelefone3() {
        return this.telefone3;
    }

    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
    }

    public Integer getCidadeId() {
        return this.cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }


    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
