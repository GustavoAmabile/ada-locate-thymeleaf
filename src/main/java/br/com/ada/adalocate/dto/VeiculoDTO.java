package br.com.ada.adalocate.dto;


import br.com.ada.adalocate.model.TipoVeiculo;
import br.com.ada.adalocate.model.Veiculo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoDTO {

    @NotBlank
    @NotNull
    private String placa;
    @NotBlank
    @NotNull
    private String marca;
    @NotBlank
    @NotNull
    private String modelo;
    private TipoVeiculo tipoVeiculo;
    private Boolean disponivel;
    public Veiculo toVeiculo() {
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(this.placa);
        veiculo.setMarca(this.marca);
        veiculo.setModelo(this.modelo);
        veiculo.setTipoVeiculo(this.tipoVeiculo);
        veiculo.setDisponivel(true);
        return veiculo;
    }

    public Veiculo toVeiculo(Veiculo veiculo) {

        veiculo.setPlaca(this.placa);
        veiculo.setMarca(this.marca);
        veiculo.setModelo(this.modelo);
        veiculo.setTipoVeiculo(this.tipoVeiculo);
        veiculo.setDisponivel(true);
        return veiculo;
    }


    public void recuperarVeiculo(Veiculo veiculo) {
        this.placa = veiculo.getPlaca();
        this.marca = veiculo.getMarca();
        this.modelo = veiculo.getModelo();
        this.tipoVeiculo = veiculo.getTipoVeiculo();
        this.disponivel = veiculo.getDisponivel();
    }

}
