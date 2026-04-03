package dev.rafael.GaragemAPTO.Pessoa;

import org.springframework.stereotype.Component;

@Component
public class PessoaMapper {

	public PessoaModel map(PessoaDTO pessoaDTO) {
		PessoaModel pessoaModel = new PessoaModel();
		pessoaModel.setId(pessoaDTO.getId());
		pessoaModel.setNome(pessoaDTO.getNome());
		pessoaModel.setCpf(pessoaDTO.getCpf());
		pessoaModel.setEmail(pessoaDTO.getEmail());
		pessoaModel.setCarro(pessoaDTO.getCarro());

		return pessoaModel;
	}

	public PessoaDTO map(PessoaModel pessoaModel) {
		PessoaDTO pessoaDTO = new PessoaDTO();
		pessoaDTO.setId(pessoaModel.getId());
		pessoaDTO.setNome(pessoaModel.getNome());
		pessoaDTO.setCpf(pessoaModel.getCpf());
		pessoaDTO.setEmail(pessoaModel.getEmail());
		pessoaDTO.setCarro(pessoaModel.getCarro());

		return pessoaDTO;
	}

}
