package br.com.ada.adalocate.controller;



import br.com.ada.adalocate.dto.VeiculoDTO;
import br.com.ada.adalocate.model.TipoVeiculo;
import br.com.ada.adalocate.model.Veiculo;
import br.com.ada.adalocate.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping("/veiculos")
    public ModelAndView index() {
        List<Veiculo> listaVeiculos = this.veiculoService.listarTodos();
        ModelAndView modelAndView = new ModelAndView("veiculos/index");
        modelAndView.addObject("veiculos", listaVeiculos);
        return modelAndView;
    }

    @GetMapping("/veiculos/novo")
    public ModelAndView atualizarVeiculos() {
        ModelAndView modelAndView = new ModelAndView("veiculos/novo");
        modelAndView.addObject("tipoVeiculo", TipoVeiculo.values());
        modelAndView.addObject("veiculoDTO", new VeiculoDTO());
        return modelAndView;
    }

    @PostMapping("/veiculos")
    public ModelAndView criarVeiculo(@Valid VeiculoDTO veiculoDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("veiculos/novo");
            modelAndView.addObject("tipoVeiculo", TipoVeiculo.values());
            return modelAndView;
        }
        else {
            Veiculo veiculo = veiculoDTO.toVeiculo();
            this.veiculoService.createVeiculo(veiculo);

            return new ModelAndView("redirect:/veiculos");
        }
    }
}
