package com.dev.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.entity.Produto;
import com.dev.backend.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> buscarTodos() {
        return repository.findAll();
    }

    public Produto inserir(Produto obj) {
        obj.setDataCriacao(new Date());
        return repository.saveAndFlush(obj);
    }

    public Produto alterar(Produto obj) {
        obj.setDataAtualizacao(new Date());
        return repository.saveAndFlush(obj);
    }

    public void excluir(Long id) {
        Produto obj = repository.findById(id).get();
        repository.delete(obj);
    }
}
