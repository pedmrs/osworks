package com.pedro.osworks.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.osworks.domain.exception.NegocioException;
import com.pedro.osworks.domain.model.Cliente;
import com.pedro.osworks.domain.model.OrdemServico;
import com.pedro.osworks.domain.model.StatusOrdemServico;
import com.pedro.osworks.domain.repository.ClienteRepository;
import com.pedro.osworks.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado."));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatusOrdemServico(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepository.save(ordemServico);
	}
}