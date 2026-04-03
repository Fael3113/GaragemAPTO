package dev.rafael.GaragemAPTO.Carros;

import dev.rafael.GaragemAPTO.Pessoa.PessoaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarrosDTO {

	private Long id;
	private String nome;
	private String marca;
	private String placa;
	private PessoaModel pessoa;

}
