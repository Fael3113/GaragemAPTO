package dev.rafael.GaragemAPTO.Pessoa;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	private final PessoaService pessoaService;

	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	@PostMapping("/criar")
	@Operation(summary = "Cria nova pessoa", description = "Essa rota permite a criação de novas pessoas" +
			" e insere no banco de dados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Erro na criação da pessoa")
	})
	public ResponseEntity<?> criarPessoa(
			@Parameter(description = "Usuário fornece no corpo da requisição os dados para criação, " +
					" é preciso que todos os parametros sejam fornecidos, o que não for fornecido será nulificado")
			@RequestBody PessoaDTO pessoa) {
		PessoaDTO pessoaDTO = pessoaService.criarPessoa(pessoa);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(pessoaDTO);
	}

	@GetMapping("/listar")
	@Operation(summary = "Listar as pessoas", description = "Essa rota permite a listagem de todos as pessoas" +
			" do banco de dados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pessoas encontradas com sucesso"),
			@ApiResponse(responseCode = "204", description = "Não há pessoas na lista")
	})
	public ResponseEntity<?> listarPessoas() {
		List<PessoaDTO> pessoaDTO = pessoaService.listarPessoas();

		if (pessoaDTO.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(pessoaDTO);
	}

	@GetMapping("/listar/{id}")
	@Operation(summary = "Listar a pessoa por id", description = "Essa rota permite encontrar pessoas especificas via ID" +
			" do banco de dados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pessoa encontrada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
	})
	public ResponseEntity<?> listarPessoaPorId(
			@Parameter(description = "Usuário fornece o id no caminho da requisição")
			@PathVariable Long id) {
		PessoaDTO pessoaDTO = pessoaService.listarPessoaPorId(id);

		if(pessoaDTO == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Pessoa com o ID "+ id +" não encontrado");
		}

		return ResponseEntity.ok(pessoaDTO);
	}

	@PutMapping("/alterar/{id}")
	@Operation(summary = "Alterar a pessoa por id", description = "Essa rota permite alterar pessoas especificas via ID" +
			" do banco de dados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pessoa alterada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
	})
	public ResponseEntity<?> alterarNinjaPorId(
			@Parameter(description = "Usuário fornece o id no caminho da requisição")
			@PathVariable Long id,
			@Parameter(description = "Usuário fornece no corpo da requisição os dados atualizados, no caso de ser um 'PUT'" +
					" é preciso que todos os parametros sejam fornecidos, o que não for fornecido será nulificado")
			@RequestBody PessoaDTO pessoaAtualizada) {
		PessoaDTO pessoaDTO = pessoaService.atualizarPessoaPorId(id, pessoaAtualizada);

		if (pessoaDTO == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Pessoa com ID "+id+" nao encontrado");
		}

		return ResponseEntity.ok(pessoaDTO);
	}

	@DeleteMapping("/deletar/{id}")
	@Operation(summary = "Deletar a pessoa por id", description = "Essa rota permite deletar pessoas especificas via ID" +
			" do banco de dados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pessoa deletada com sucesso"),
			@ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
	})
	public ResponseEntity<?> deletarNinjaPorId(
			@Parameter(description = "Usuário fornece o id no caminho da requisição")
			@PathVariable Long id) {
		PessoaDTO pessoaDTO = pessoaService.listarPessoaPorId(id);

		if (pessoaDTO == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Pessoa com o ID "+ id +" não encontrado");
		}

		pessoaService.deletarPessoaPorId(id);
		return ResponseEntity.ok("Pessoa com ID "+id+" deletado com sucesso");
	}

}
