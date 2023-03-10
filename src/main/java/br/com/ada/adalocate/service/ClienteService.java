package br.com.ada.adalocate.service;


import br.com.ada.adalocate.model.Cliente;
import br.com.ada.adalocate.model.Veiculo;
import br.com.ada.adalocate.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void createCliente(Cliente cliente) {
        this.clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return this.clienteRepository.findAll();
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return this.clienteRepository.findById(id);
    }

    public void removerClientePorId(Long id) {
        this.clienteRepository.deleteById(id);
    }

    public Page<Cliente> listarPaginado(Integer numeroPagina, Integer tamanhoPagina) {
        return this.clienteRepository
                .findAll(PageRequest.of(numeroPagina, tamanhoPagina, Sort.by(Sort.Order.asc("id"))));
    }
}
