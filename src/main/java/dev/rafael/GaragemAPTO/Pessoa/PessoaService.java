package dev.rafael.GaragemAPTO.Pessoa;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {

	private final PessoaRepository pessoaRepository;
	private final PessoaMapper pessoaMapper;

	public PessoaService(PessoaRepository pessoaRepository, PessoaMapper pessoaMapper) {
		this.pessoaRepository = pessoaRepository;
		this.pessoaMapper = pessoaMapper;
	}

	public PessoaDTO criarPessoa(PessoaDTO pessoaDTO) {
		PessoaModel pessoaModel = pessoaMapper.map(pessoaDTO);
		return pessoaMapper.map(pessoaRepository.save(pessoaModel));
	}

	public List<PessoaDTO> listarPessoas() {
		List<PessoaModel> pessoasModel = pessoaRepository.findAll();
		return pessoasModel.stream()
				.map(pessoaMapper::map)
				.collect(Collectors.toList());
	}

	public PessoaDTO listarPessoaPorId(Long id) {
		Optional<PessoaModel> pessoaModel = pessoaRepository.findById(id);
		return pessoaModel.map(pessoaMapper::map)
				.orElse(null);
	}

	public void deletarPessoaPorId(Long id) {
		pessoaRepository.deleteById(id);
	}

	public PessoaDTO atualizarPessoaPorId(Long id, PessoaDTO pessoaDTO) {
		Optional<PessoaModel> pessoaModel = pessoaRepository.findById(id);
		if (pessoaModel.isPresent()) {
			PessoaModel pessoaAtualizada = pessoaMapper.map(pessoaDTO);
			pessoaAtualizada.setId(id);
			PessoaModel pessoaSalva = pessoaRepository.save(pessoaAtualizada);
			return pessoaMapper.map(pessoaSalva);
		}
		return null;
	}
}
