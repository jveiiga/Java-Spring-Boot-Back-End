package com.example.cursomc.resources;

import com.example.cursomc.domain.Cliente;
import com.example.cursomc.dto.ClienteDTO;
import com.example.cursomc.dto.ClienteNewDTO;
import com.example.cursomc.services.ClienteService;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    // GET (ID)
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {

        Cliente obj = clienteService.find(id);

        return ResponseEntity.ok().body(obj);
    }

    //POST
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Cliente> insert(@Valid @RequestBody ClienteNewDTO objDto) {

        Cliente obj = clienteService.fromDTO(objDto);
        obj = clienteService.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(obj.getId())
                    .toUri();

        return ResponseEntity.created(uri).build();
    }

    // PUT
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Cliente> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
        Cliente obj = clienteService.fromDTO(objDto);
        obj.setId(id);
        obj = clienteService.update(obj);

        return ResponseEntity.noContent().build();
    }

    //DELETE
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Cliente> delete(@PathVariable Integer id) {

        clienteService.delete(id);
        
        return ResponseEntity.noContent().build();
    } 

    // GET
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {

        List< Cliente> list = clienteService.findall();
        List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    // GET (LISTPAGE)
    @RequestMapping(value="/page", method=RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
        @RequestParam(value="page", defaultValue="0") Integer page,
        @RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
        @RequestParam(value="orderBy", defaultValue="id")String orderBy,
        @RequestParam(value="direction", defaultValue="ASC")String direction) {
        
        Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direction);

        Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));

        return ResponseEntity.ok().body(listDto);
    }
}
