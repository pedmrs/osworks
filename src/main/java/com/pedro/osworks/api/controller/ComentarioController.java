package com.pedro.osworks.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.osworks.api.model.ComentarioInputModel;
import com.pedro.osworks.api.model.ComentarioModel;
import com.pedro.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.pedro.osworks.domain.model.Comentario;
import com.pedro.osworks.domain.model.OrdemServico;
import com.pedro.osworks.domain.repository.OrdemServicoRepository;
import com.pedro.osworks.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServicoService;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping
	public List<ComentarioModel> listar(@PathVariable Long ordemServicoId) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de Serviço não encontrada."));
		
		return toCollectionModel(ordemServico.getComentarios());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioModel adicionar(@PathVariable Long ordemServicoId,
			@Valid @RequestBody ComentarioInputModel comentarioInputModel) {
		Comentario comentario = gestaoOrdemServicoService.adicionarComentario(ordemServicoId, comentarioInputModel.getDescricao());
		
		return toModel(comentario);
	}
	
	private ComentarioModel toModel(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioModel.class);
	}
	
	private List<ComentarioModel> toCollectionModel(List<Comentario> comentarios) {
		return comentarios.stream()
				.map(comentario -> toModel(comentario))
				.collect(Collectors.toList());
	}
}
