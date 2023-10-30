package com.dev.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.entity.Marca;
import com.dev.backend.repository.MarcaRepository;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository repository;

    public List<Marca> buscarTodos() {
        return repository.findAll();
    }

    public Marca inserir(Marca marca) {
        marca.setDataAtualizacao(new Date());
        return repository.saveAndFlush(marca);
    }

    public Marca alterar(Marca marca) {
        marca.setDataAtualizacao(new Date());
        return repository.saveAndFlush(marca);
    }

    public void excluir(Long id) {
        Marca obj = repository.findById(id).get();
        repository.delete(obj);
    }
}