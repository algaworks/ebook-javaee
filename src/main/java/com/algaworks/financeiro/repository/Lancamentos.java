package com.algaworks.financeiro.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.algaworks.financeiro.model.Lancamento;

public class Lancamentos {

	private EntityManager manager;

	@Inject
	public Lancamentos(EntityManager manager) {
		this.manager = manager;
	}

	public Lancamento porId(Long id) {
		return manager.find(Lancamento.class, id);
	}
	
	public List<String> descricoesQueContem(String descricao) {
		TypedQuery<String> query = manager.createQuery(
				"select distinct descricao from Lancamento "
				+ "where upper(descricao) like upper(:descricao)", 
				String.class);
		query.setParameter("descricao", "%" + descricao + "%");
		return query.getResultList();
	}
	
	public List<Lancamento> todos() {
		TypedQuery<Lancamento> query = manager.createQuery(
				"from Lancamento", Lancamento.class);
		return query.getResultList();
	}

	public void adicionar(Lancamento lancamento) {
		this.manager.persist(lancamento);
	}
	
	public Lancamento guardar(Lancamento lancamento) {
		return this.manager.merge(lancamento);
	}
	
	public void remover(Lancamento lancamento) {
		this.manager.remove(lancamento);
	}

}