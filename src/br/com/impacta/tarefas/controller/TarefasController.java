package br.com.impacta.tarefas.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
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
		return "tarefa/adicionada";
	}

}
