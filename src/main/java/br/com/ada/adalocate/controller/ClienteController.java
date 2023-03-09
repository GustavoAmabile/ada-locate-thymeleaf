package br.com.ada.adalocate.controller;

import br.com.ada.adalocate.dto.ClienteDTO;
import br.com.ada.adalocate.model.Cliente;
import br.com.ada.adalocate.model.TipoPessoa;
import br.com.ada.adalocate.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("")
    public ModelAndView index() {
        List<Cliente> listaClientes = this.clienteService.listarTodos();
        ModelAndView modelAndView = new ModelAndView("clientes/index");
        modelAndView.addObject("clientes", listaClientes);
        return modelAndView;
    }

    @GetMapping("/novo")
    public ModelAndView atualizarCliente(ClienteDTO clienteDTO) {
        ModelAndView modelAndView = new ModelAndView("clientes/novo");
        modelAndView.addObject("tipoPessoa", TipoPessoa.values());
        return modelAndView;
    }

    @PostMapping("")
    public ModelAndView criarCliente(@Valid ClienteDTO clienteDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("clientes/novo");
            modelAndView.addObject("tipoPessoa", TipoPessoa.values());
            return modelAndView;
        } else {
            Cliente cliente= clienteDTO.toCliente();
            this.clienteService.createCliente(cliente);

            return new ModelAndView("redirect:/clientes");
        }
    }

    @GetMapping("/{id}")
    public ModelAndView detalharCliente(@PathVariable Long id) {
        Optional<Cliente> optional =  this.clienteService.buscarClientePorId(id);
        if (optional.isPresent()) {
            Cliente cliente = optional.get();
            ModelAndView modelAndView = new ModelAndView("clientes/cliente");
            modelAndView.addObject("cliente", cliente);
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/clientes");
        }
    }

    @GetMapping("/{id}/atualizar")
    public ModelAndView atualizarCliente(@PathVariable Long id, ClienteDTO clienteDTO) {
        Optional<Cliente> optional = this.clienteService.buscarClientePorId(id);

        if (optional.isPresent()) {
            Cliente cliente = optional.get();
            clienteDTO.recuperarCliente(cliente);
            ModelAndView modelAndView = new ModelAndView("clientes/atualizar");
            modelAndView.addObject("tipoPessoa", TipoPessoa.values());
            modelAndView.addObject("clienteId", cliente.getId());
            return modelAndView;
        } else {

            return new ModelAndView("redirect:/clientes");
        }
    }

    @PostMapping("/{id}")
    public ModelAndView atualizarCliente(@PathVariable Long id, @Valid ClienteDTO clienteDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("clientes/atualizar");
            modelAndView.addObject("clienteId", id);
            modelAndView.addObject("tipoPessoa", TipoPessoa.values());
           return modelAndView;
        } else {
            Optional<Cliente> optional = this.clienteService.buscarClientePorId(id);
            if (optional.isPresent()) {
                Cliente cliente = clienteDTO.toCliente(optional.get());
                this.clienteService.createCliente(cliente);
                return new ModelAndView("redirect:/clientes/" + cliente.getId());

            } else {
                return new ModelAndView("redirect:/clientes");
            }

        }
    }


}
