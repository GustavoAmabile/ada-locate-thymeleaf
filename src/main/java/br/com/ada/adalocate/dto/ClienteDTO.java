package br.com.ada.adalocate.dto;

import br.com.ada.adalocate.model.Cliente;
import br.com.ada.adalocate.model.TipoPessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    @NotBlank
    @NotNull
    private String nome;
    private TipoPessoa tipoPessoa;
    @NotBlank
    @NotNull
    private String documento;
    @NotBlank
    @NotNull
    private String email;

    public Cliente toCliente() {
        Cliente cliente = new Cliente();

        cliente.setNome(this.nome);
        cliente.setTipoPessoa(this.tipoPessoa);
        cliente.setDocumento(this.documento);
        cliente.setEmail(this.email);

        return cliente;
    }

    public Cliente toCliente(Cliente cliente) {
        cliente.setNome(this.nome);
        cliente.setTipoPessoa(this.tipoPessoa);
        cliente.setDocumento(this.documento);
        cliente.setEmail(this.email);
        return cliente;
    }


    public void recuperarCliente(Cliente cliente) {
        this.nome = cliente.getNome();
        this.tipoPessoa = cliente.getTipoPessoa();
        this.documento = cliente.getDocumento();
        this.email = cliente.getEmail();
    }

}

