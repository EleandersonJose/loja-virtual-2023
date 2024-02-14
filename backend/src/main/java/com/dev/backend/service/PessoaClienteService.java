package com.dev.backend.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.dto.PessoaClienteRequestDTO;
import com.dev.backend.entity.Pessoa;
import com.dev.backend.repository.PessoaClienteRepository;

@Service
public class PessoaClienteService {

    @Autowired
    private PessoaClienteRepository pessoaClienteRepository;

    @Autowired
    private PermissaoPessoaService permissaoPessoaService;

    @Autowired
    private EmailService emailService;

    public Pessoa registrar(PessoaClienteRequestDTO pessoaClienteRequestDTO) {
        Pessoa pessoa = new PessoaClienteRequestDTO().converter(pessoaClienteRequestDTO);
        pessoa.setDataCriacao(new Date());
        Pessoa objetoNovo = pessoaClienteRepository.saveAndFlush(pessoa);
        permissaoPessoaService.vincularPessoaPermissaoCliente(objetoNovo);

        // emailService.enviarEmailTexto(objetoNovo.getEmail(),
        // "Cadastro na Loja realizado, em breve voce recebera a senha de acesso por
        // email",
        // "O registro foi realizado com sucesso");
        Map<String, Object> proprMap = new HashMap<>();
        proprMap.put("nome", objetoNovo.getNome());
        proprMap.put("mensagem", "O registro foi realizado com sucesso");
        emailService.enviarEmailTemplate(objetoNovo.getEmail(), "Cadastro na Loja realizado", proprMap);

        return objetoNovo;
    }

}
