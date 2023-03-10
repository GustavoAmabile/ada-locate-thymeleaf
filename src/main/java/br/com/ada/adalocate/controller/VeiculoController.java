package br.com.ada.adalocate.controller;


import br.com.ada.adalocate.dto.VeiculoDTO;
import br.com.ada.adalocate.model.TipoVeiculo;
import br.com.ada.adalocate.model.Veiculo;
import br.com.ada.adalocate.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(value = "/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping("")
    public ModelAndView index(
            @RequestParam(defaultValue = "1", value = "page") Integer numeroPagina,
            @RequestParam(defaultValue = "3", value = "size") Integer tamanhoPagina) {
        List<Veiculo> listaVeiculos = this.veiculoService.listarTodos();
        ModelAndView modelAndView = new ModelAndView("veiculos/index");
        Page<Veiculo> veiculoPage = this.veiculoService.listarPaginado(numeroPagina-1, tamanhoPagina);
        modelAndView.addObject("veiculos", veiculoPage.getContent());
        modelAndView.addObject("totalPages", veiculoPage.getTotalPages());
        modelAndView.addObject("currentPage", numeroPagina);
        modelAndView.addObject("pageSize", veiculoPage.getSize());
        return modelAndView;
    }

    @GetMapping("/novo")
    public ModelAndView atualizarVeiculos(VeiculoDTO veiculoDTO) {
        ModelAndView modelAndView = new ModelAndView("veiculos/novo");
        modelAndView.addObject("tipoVeiculo", TipoVeiculo.values());
        return modelAndView;
    }

    @PostMapping("")
    public ModelAndView criarVeiculo(@Valid VeiculoDTO veiculoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("veiculos/novo");
            modelAndView.addObject("tipoVeiculo", TipoVeiculo.values());
            return modelAndView;
        } else {
            Veiculo veiculo = veiculoDTO.toVeiculo();
            this.veiculoService.createVeiculo(veiculo);

            return new ModelAndView("redirect:/veiculos");
        }
    }

    @GetMapping("/{id}")
    public ModelAndView exibirVeiculo(@PathVariable Long id) {
        Optional<Veiculo> optional = this.veiculoService.buscarVeiculoPorId(id);
        if (optional.isPresent()) {
            Veiculo veiculo = optional.get();
            ModelAndView modelAndView = new ModelAndView("veiculos/veiculo");
            modelAndView.addObject("veiculo", veiculo);
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/veiculos");
        }
    }

    @GetMapping("/{id}/atualizar")
    public ModelAndView atualizarVeiculo(@PathVariable Long id, VeiculoDTO veiculoDTO) {
        Optional<Veiculo> optional = this.veiculoService.buscarVeiculoPorId(id);

        if (optional.isPresent()) {
            Veiculo veiculo = optional.get();
            veiculoDTO.recuperarVeiculo(veiculo);
            ModelAndView modelAndView = new ModelAndView("veiculos/atualizar");
            modelAndView.addObject("tipoVeiculo", TipoVeiculo.values());
            modelAndView.addObject("veiculoId", veiculo.getId());
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/veiculos");
        }
    }

    @PostMapping("/{id}")
    public ModelAndView atualizarVeiculo(@PathVariable Long id, @Valid VeiculoDTO veiculoDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("veiculos/atualizar");
            modelAndView.addObject("veiculoId", id);
            modelAndView.addObject("tipoVeiculo", TipoVeiculo.values());
            return modelAndView;
        } else {
            Optional<Veiculo> optional = this.veiculoService.buscarVeiculoPorId(id);
            if (optional.isPresent()) {
                Veiculo veiculo = veiculoDTO.toVeiculo(optional.get());
                this.veiculoService.createVeiculo(veiculo);
                return new ModelAndView("redirect:/veiculos/" + veiculo.getId());
            } else {
                return new ModelAndView("redirect:/veiculos");
            }
        }
    }

    @GetMapping("/{id}/deletar")
    public String deletar(@PathVariable Long id) {
        this.veiculoService.removerVeiculoPorId(id);
        return "redirect:/veiculos";
    }
}
