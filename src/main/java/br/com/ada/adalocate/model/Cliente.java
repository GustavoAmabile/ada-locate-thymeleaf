package br.com.ada.adalocate.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_clientes")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(nullable = false)
    private String nome;
    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;
    @NonNull
    @Column(name="documento", unique = true, nullable = false)
    private String documento;
    @NonNull
    @Column(nullable = false)
    private String email;


}
