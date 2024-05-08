package com.example.cursomc.services;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.dto.CategoriaDTO;
import com.example.cursomc.repositories.CategoriaRepository;
import com.example.cursomc.services.exceptions.DataIntegrityException;
import com.example.cursomc.services.exceptions.ObjectNotFoundExpection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Camada de serviço de acesse aos dados
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    //Busca categoria por id
    public Categoria find(Integer id) {

        Optional<Categoria> obj = categoriaRepository.findById(id);

        if (obj.isEmpty()) {
            throw new ObjectNotFoundExpection("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName());
        }
        return  obj.get();
    }

    //Adciona uma nova categoria
    public Categoria insert(Categoria obj) {

        obj.setId(null);

        return categoriaRepository.save(obj);
    }

    //Edita uma categoria
    public Categoria update(Categoria obj) {

        Categoria newObj = find(obj.getId());
        updateData(newObj, obj);

        return categoriaRepository.save(newObj);
    }

    //Deleta uma categoria
    public void delete(Integer id) {
        find(id);
        try{
            categoriaRepository.deleteById(id);
        } 
        catch (DataIntegrityViolationException e) {

            throw new  DataIntegrityException("Não é possível excluir uma categoria que possuí produtos!");
        }
    }

    //Lista todas as categorias
    public List<Categoria> findall() {

        return categoriaRepository.findAll();
    }

    //Lista todas as categorias por página
    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.by(Sort.Direction.fromString(direction), orderBy));

        return categoriaRepository.findAll(pageRequest);
    } 

    // Estancia o objeto a partir do DTO
    public Categoria fromDTO(CategoriaDTO objDto) {

        return new Categoria(objDto.getId(), objDto.getNome());
    }

    // Atualiza os atributos permitidos
    private void updateData(Categoria newObj, Categoria obj) {
        newObj.setNome(obj.getNome()); 
    }
}
