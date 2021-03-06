package br.com.impacta.tarefas.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.impacta.tarefas.dao.TarefaDAO;
import br.com.impacta.tarefas.model.Tarefa;

@Controller
public class TarefasController {
	
	@RequestMapping("novaTarefa")
	public String form() {
		return "tarefa/formulario";
	}

	@RequestMapping("adicionaTarefa")
	public String adiciona(@Valid Tarefa tarefa, BindingResult result) {

		if (result.hasFieldErrors("descricao")) {
			return "tarefa/formulario";
		}

		TarefaDAO dao = new TarefaDAO();
		dao.adicionaTarefa(tarefa);
		return "redirect:listaTarefa";
	}
	
	@RequestMapping("listaTarefa")
	public String lista(Model model){
		TarefaDAO dao = new TarefaDAO();
		model.addAttribute("tarefas", dao.getList());
		return "tarefa/lista";
	} 
	
	@RequestMapping("removeTarefa")
	public String remove(Tarefa tarefa) {
	  TarefaDAO dao = new TarefaDAO();
	  dao.excluiTarefa(tarefa);
	  
	  return "redirect:listaTarefa";
	}
	
	@RequestMapping("mostraTarefa")
	public String mostra(Long id, Model model) {
	  TarefaDAO dao = new TarefaDAO();
	  model.addAttribute("tarefa", dao.buscaPorId(id));
	  return "tarefa/mostra";
	}

	@RequestMapping("alteraTarefa")
	public String altera(Tarefa tarefa) {
	  TarefaDAO dao = new TarefaDAO();
	  dao.alteraTarefa(tarefa);
	  return "forward:listaTarefa";

	}

}
