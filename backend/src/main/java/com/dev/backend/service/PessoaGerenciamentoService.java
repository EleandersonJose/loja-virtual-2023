package com.dev.backend.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.entity.Pessoa;
import com.dev.backend.repository.PessoaRepository;

@Service
public class PessoaGerenciamentoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EmailService emailService;

    public String solicitarCodigo(String email) {

        Pessoa pessoa = pessoaRepository.findByEmail(email);
        pessoa.setCodigoRecuperacaoSenha(getCodigoRecuperacaoSenha(pessoa.getId()));
        pessoa.setDataEnvioCodigo(new Date());
        pessoaRepository.saveAndFlush(pessoa);

        emailService.enviarEmailTexto(pessoa.getEmail(), "Código de Recuperação de Senha",
                "Segue abaixo o código para recuperação de senha " + pessoa.getCodigoRecuperacaoSenha());

        return "Código enviado!";
    }

    public String alterarSenha(Pessoa pessoa) {

        Pessoa pessoaBanco = pessoaRepository.findByEmailAndCodigoRecuperacaoSenha(pessoa.getEmail(),
                pessoa.getCodigoRecuperacaoSenha());

        if (pessoaBanco != null) {
            Date diferenca = new Date(new Date().getTime() - pessoaBanco.getDataEnvioCodigo().getTime());

            if (diferenca.getTime() / 1000 < 900) {
                // depois que implementar o Spring Security, é necessario criptografar a senha!
                pessoaBanco.setSenha(pessoa.getSenha());
                pessoaBanco.setCodigoRecuperacaoSenha(null);
                pessoaRepository.saveAndFlush(pessoaBanco);
                return "Senha Alterada Com Sucesso!";
            } else {
                return "Tempo expirado, solicite um novo codigo";
            }
        } else {
            return "Email ou codigo nao encontrados!";
        }
    }

    private String getCodigoRecuperacaoSenha(Long id) {
        DateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssmm");

        return format.format(new Date()) + id;
    }

}
