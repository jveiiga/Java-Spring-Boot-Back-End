package com.example.cursomc.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.cursomc.domain.Categoria;
import com.example.cursomc.domain.Produto;
import com.example.cursomc.repositories.CategoriaRepository;
import com.example.cursomc.repositories.ProdutoRepository;
import com.example.cursomc.services.exceptions.ObjectNotFoundExpection;

@Service
public class ProdutoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository; 

    public Produto find(Integer id) {
        
        Optional<Produto> obj = produtoRepository.findById(id);
        
        if (obj.isEmpty()) {
            throw new ObjectNotFoundExpection("Objeto não encontrado! Id: " + id + "Tipo: " + Produto.class.getName());
        }
        return obj.get();
    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.by(Sort.Direction.fromString(direction), orderBy));

        List<Categoria> categorias = categoriaRepository.findAllById(ids);

        return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }
}
