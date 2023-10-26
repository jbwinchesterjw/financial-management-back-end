package com.financial.management.service;

import com.financial.management.model.Lancamento;
import com.financial.management.model.Pessoa;
import com.financial.management.repository.LancamentoRepository;
import com.financial.management.repository.PessoaRepository;
import com.financial.management.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired 
	private LancamentoRepository lancamentoRepository;

	public Lancamento salvar(Lancamento lancamento) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
		if (pessoa.isEmpty() || pessoa.get().isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		return lancamentoRepository.save(lancamento);
	}

	public Lancamento editarLancamento(Long codigo, Lancamento lancamento){
		Lancamento lancamentoSalvo = buscarLancamentoPeloCodigo(codigo);

		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");
		return lancamentoRepository.save(lancamentoSalvo);
	}

	private Lancamento buscarLancamentoPeloCodigo(Long codigo) {
        return lancamentoRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));
	}

}
