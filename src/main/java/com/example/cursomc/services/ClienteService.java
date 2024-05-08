package com.example.cursomc.services;

import com.example.cursomc.domain.Cidade;
import com.example.cursomc.domain.Cliente;
import com.example.cursomc.domain.Endereco;
import com.example.cursomc.domain.enums.TipoCliente;
import com.example.cursomc.dto.ClienteDTO;
import com.example.cursomc.dto.ClienteNewDTO;
import com.example.cursomc.repositories.ClienteRepository;
import com.example.cursomc.repositories.EnderecoRepository;
import com.example.cursomc.services.exceptions.DataIntegrityException;
import com.example.cursomc.services.exceptions.ObjectNotFoundExpection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    // Busca um cliente pelo ID
    public Cliente find(Integer id) {

        Optional<Cliente> obj = clienteRepository.findById(id);

        if (obj.isEmpty()) {
            throw new ObjectNotFoundExpection("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
        }
        return obj.get();
    }

    // Adciona um novo cliente
    @Transactional
    public Cliente insert(Cliente obj) {

        obj.setId(null);
        obj = clienteRepository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());

        return obj;
    }

    // Edita um cliente
    public Cliente update(Cliente obj) {

        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);

        return clienteRepository.save(newObj);
    }

    // Deleta um cliente
    public void delete(Integer id) {
        find(id);
        try{
            clienteRepository.deleteById(id);
        } 
        catch (DataIntegrityViolationException e) {

            throw new  DataIntegrityException("Não é possível excluir por que há pedidos relacionados!");
        }
    }

    // Lista todos os clientes
    public List<Cliente> findall() {

        return clienteRepository.findAll();
    }

    // Lista todos os clientes por página
    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.by(Sort.Direction.fromString(direction), orderBy));

        return clienteRepository.findAll(pageRequest);
    } 

    // Estancia o objeto a partir do DTO
    public Cliente fromDTO(ClienteDTO objDto) {

        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }

    public Cliente fromDTO(ClienteNewDTO objDto) {

        Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));

        Cidade cidade = new Cidade(objDto.getCidadeId(), null, null);
        
        Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cliente, cidade);
        cliente.getEnderecos().add(endereco);

        cliente.getTelefones().add(objDto.getTelefone1());

        if (objDto.getTelefone2() != null) {
            cliente.getTelefones().add(objDto.getTelefone2());
        }

        if (objDto.getTelefone3() != null) {
            cliente.getTelefones().add(objDto.getTelefone3());
        }

        return cliente;
    }

    // Atualiza os atributos permitidos
    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
