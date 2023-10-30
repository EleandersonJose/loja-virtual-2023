package com.dev.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.entity.Permissao;
import com.dev.backend.repository.PermissaoRepository;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository repository;

    public List<Permissao> buscarTodos() {
        return repository.findAll();
    }

    public Permissao inserir(Permissao obj) {
        obj.setDataCriacao(new Date());
        return repository.saveAndFlush(obj);
    }

    public Permissao alterar(Permissao obj) {
        obj.setDataAtualizacao(new Date());
        return repository.saveAndFlush(obj);
    }

    public void excluir(Long id) {
        Permissao obj = repository.findById(id).get();
        repository.delete(obj);
    }

}
