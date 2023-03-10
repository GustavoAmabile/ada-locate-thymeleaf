package br.com.ada.adalocate.controller;


import br.com.ada.adalocate.model.Aluguel;
import br.com.ada.adalocate.model.Cliente;
import br.com.ada.adalocate.model.Veiculo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@Controller
public class AluguelController {

   @GetMapping("/alugueis")
    public ModelAndView index() {
       Aluguel janeiro = new Aluguel();
       Cliente cliente = new Cliente();
       Veiculo veiculo = new Veiculo();
       BigDecimal valorDiaria = new BigDecimal(100);
       BigDecimal valorAluguel = new BigDecimal(1000);
       BigDecimal valorDesconto = new BigDecimal(0);
       janeiro.setCliente(cliente);
       janeiro.setVeiculo(veiculo);
       janeiro.setDiarias(10L);
       janeiro.setRetirada(LocalDate.now());
       janeiro.setDevolucao(LocalDate.of(2023, Month.MARCH, 16));
       janeiro.setValorDiaria(valorDiaria);
       janeiro.setValorAluguel(valorAluguel);
       janeiro.setDesconto(valorDesconto);
      List<Aluguel> alugueis = Arrays.asList(janeiro);

       ModelAndView modelAndView = new ModelAndView("alugueis/index");
       modelAndView.addObject("alugueis", alugueis);
       return modelAndView;
   }


}
