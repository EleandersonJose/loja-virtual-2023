package com.dev.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.entity.PermissaoPessoa;
import com.dev.backend.repository.PermissaoPessoaRepository;

@Service
public class PermissaoPessoaService {

    @Autowired
    private PermissaoPessoaRepository repository;

    public List<PermissaoPessoa> buscarTodos() {
        return repository.findAll();
    }

    public PermissaoPessoa inserir(PermissaoPessoa obj) {
        obj.setDataCriacao(new Date());
        return repository.saveAndFlush(obj);
    }

    public PermissaoPessoa alterar(PermissaoPessoa obj) {
        obj.setDataAtualizacao(new Date());
        return repository.saveAndFlush(obj);
    }

    public void excluir(Long id) {
        PermissaoPessoa obj = repository.findById(id).get();
        repository.delete(obj);
    }
}
