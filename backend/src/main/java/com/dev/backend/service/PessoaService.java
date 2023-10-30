package com.dev.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.entity.Pessoa;
import com.dev.backend.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public List<Pessoa> buscarTodos() {
        return repository.findAll();
    }

    public Pessoa inserir(Pessoa obj) {
        obj.setDataCriacao(new Date());
        return repository.saveAndFlush(obj);
    }

    public Pessoa alterar(Pessoa obj) {
        obj.setDataAtualizacao(new Date());
        return repository.saveAndFlush(obj);
    }

    public void excluir(Long id) {
        Pessoa obj = repository.findById(id).get();
        repository.delete(obj);
    }

}
