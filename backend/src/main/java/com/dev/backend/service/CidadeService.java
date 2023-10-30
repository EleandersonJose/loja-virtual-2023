package com.dev.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.entity.Cidade;
import com.dev.backend.repository.CidadeRepository;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    public List<Cidade> buscarTodos() {
        return repository.findAll();
    }

    public Cidade inserir(Cidade cidade) {
        cidade.setDataCriacao(new Date());
        return repository.saveAndFlush(cidade);
    }

    public Cidade alterar(Cidade cidade) {
        cidade.setDataAtualizacao(new Date());
        return repository.saveAndFlush(cidade);
    }

    public void excluir(Long id) {
        Cidade cidade = repository.findById(id).get();
        repository.delete(cidade);
    }
}
