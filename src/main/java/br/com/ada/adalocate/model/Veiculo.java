package br.com.ada.adalocate.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "tb_veiculos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(name = "placa", unique = true, nullable = false)
    private String placa;
    @NonNull
    @Column(nullable = false)
    private String marca;
    @NonNull
    @Column(nullable = false)
    private String modelo;
    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo;
    private Boolean disponivel;


}
