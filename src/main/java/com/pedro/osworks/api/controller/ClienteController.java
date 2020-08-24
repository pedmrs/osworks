package com.pedro.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.osworks.domain.model.Cliente;
import com.pedro.osworks.domain.repository.ClienteRepository;

@RestController
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/clientes/{clienteId}")
	public Cliente buscar(@PathVariable Long clienteId) {
		return clienteRepository.findById(clienteId).orElse(null);
	}
}
