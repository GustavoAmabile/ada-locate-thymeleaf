package br.com.ada.adalocate.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AluguelController {

    @GetMapping("/aluguel/new-model")
    public String alugarVeiculo(Model model) {
        model.addAttribute("nome", "Gu");
        return "aluguel-new";
    }

    @GetMapping("/aluguel/new")
    public ModelAndView alugarVeiculo() {
        ModelAndView modelAndView = new ModelAndView("aluguel-new");
        modelAndView.addObject("nome", "Al");
        return modelAndView;
    }
}
