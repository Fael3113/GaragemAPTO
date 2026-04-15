package dev.rafael.GaragemAPTO.Pessoa;

import dev.rafael.GaragemAPTO.Docs.PessoaControllerDoc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController implements PessoaControllerDoc {

	private final PessoaService pessoaService;

	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	@PostMapping("/criar")
	public ResponseEntity<Object> criarPessoa(@RequestBody PessoaDTO pessoa) {
		PessoaDTO pessoaDTO = pessoaService.criarPessoa(pessoa);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(pessoaDTO);
	}

	@GetMapping("/listar")
	public ResponseEntity<Object> listarPessoas() {
		List<PessoaDTO> pessoaDTO = pessoaService.listarPessoas();

		if (pessoaDTO.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(pessoaDTO);
	}

	@GetMapping("/listar/{id}")
	public ResponseEntity<Object> listarPessoaPorId(@PathVariable Long id) {
		PessoaDTO pessoaDTO = pessoaService.listarPessoaPorId(id);

		if(pessoaDTO == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Pessoa com o ID "+ id +" não encontrado");
		}

		return ResponseEntity.ok(pessoaDTO);
	}

	@PutMapping("/alterar/{id}")
	public ResponseEntity<Object> alterarPessoaPorId(@PathVariable Long id, @RequestBody PessoaDTO pessoaAtualizada) {
		PessoaDTO pessoaDTO = pessoaService.atualizarPessoaPorId(id, pessoaAtualizada);

		if (pessoaDTO == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Pessoa com ID "+id+" nao encontrado");
		}

		return ResponseEntity.ok(pessoaDTO);
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Object> deletarPessoaPorId(@PathVariable Long id) {
		PessoaDTO pessoaDTO = pessoaService.listarPessoaPorId(id);

		if (pessoaDTO == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Pessoa com o ID "+ id +" não encontrado");
		}

		pessoaService.deletarPessoaPorId(id);
		return ResponseEntity.ok("Pessoa com ID "+id+" deletado com sucesso");
	}

}
