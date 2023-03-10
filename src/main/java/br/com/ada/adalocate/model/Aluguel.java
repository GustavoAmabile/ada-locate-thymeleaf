package br.com.ada.adalocate.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="tb_alugueis")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NonNull
    @Column(nullable = false)
    private Long diarias;
    @NonNull
    @Column(nullable = false)
    private LocalDate retirada;
    @NonNull
    @Column(nullable = false)
    private LocalDate devolucao;
    @ManyToOne
    @JoinColumn(name = "veiculos_id")
    private Veiculo veiculo;
    @ManyToOne
    @JoinColumn(name = "clientes_id")
    private Cliente cliente;
    @NonNull
    @Column(nullable = false)
    private BigDecimal valorDiaria;
    @NonNull
    @Column(nullable = false)
    private BigDecimal valorAluguel;
    @NonNull
    @Column(nullable = false)
    private BigDecimal desconto;



}
