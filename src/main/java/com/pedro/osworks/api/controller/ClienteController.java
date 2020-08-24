package com.pedro.osworks.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.osworks.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		var cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("bruna");
		cliente1.setEmail("bruna@gmail.com");
		cliente1.setTelefone("21 999999999");
		
		var cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("pedro");
		cliente2.setEmail("pedro@gmail.com");
		cliente2.setTelefone("telegram");
		
		return Arrays.asList(cliente1, cliente2);
	}
}
