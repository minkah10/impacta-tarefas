package br.com.impacta.tarefas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.impacta.tarefas.model.Tarefa;

public class TarefaDAO {

	private static TarefaDAO instance;

	protected EntityManager entityManager;

	public List<Tarefa> getTarefas() {
		List<Tarefa> lista = null;
		Query query = entityManager.createQuery("select f from Tarefa f");
		lista = query.getResultList();

		return lista;

	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("tarefa");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}

	public static TarefaDAO getInstance() {
		if (instance == null) {
			instance = new TarefaDAO();
		}
		return instance;
	}

	public TarefaDAO() {
		entityManager = getEntityManager();
	}

	public void adicionaTarefa(Tarefa tarefa) {

		entityManager.getTransaction().begin();
		entityManager.persist(tarefa);
		entityManager.getTransaction().commit();

	}

	public void excluiTarefa(Tarefa tarefa) {

		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.getReference(Tarefa.class,
				tarefa.getId()));
		entityManager.getTransaction().commit();

	}

	public void alteraTarefa(Tarefa tarefa) {
		entityManager.getTransaction().begin();
		entityManager.merge(tarefa);
		entityManager.getTransaction().commit();;
	}

	@SuppressWarnings("unchecked")
	public List<Tarefa> getList() {
		Query query = entityManager.createQuery("select f from Tarefa as f");

		return query.getResultList();
	}
	
	
	
	public Tarefa buscaPorId(Long id){
		return	entityManager.find(Tarefa.class, id);
		 
		
	}

}
