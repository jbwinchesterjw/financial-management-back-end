package com.financial.management.repository.lancamento;


import com.financial.management.model.Lancamento;
import com.financial.management.repository.filter.LancamentoFilter;
import com.financial.management.repository.projection.ResumoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface LancamentoRepositoryQuery {

	/**
	 *
	 * @param lancamentoFilter
	 * @param pageable
	 * @return
	 */
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);

	/**
	 *
	 * @param lancamentoFilter
	 * @param pageable
	 * @return
	 */
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);

}
