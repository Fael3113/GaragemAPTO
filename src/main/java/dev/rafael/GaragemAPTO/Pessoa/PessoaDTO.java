package dev.rafael.GaragemAPTO.Pessoa;

import dev.rafael.GaragemAPTO.Carros.CarrosModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private CarrosModel carro;

}
