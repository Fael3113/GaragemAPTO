package dev.rafael.GaragemAPTO.Carros;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarrosController {

	private final CarrosService carrosService;

	public CarrosController(CarrosService carrosService) {
		this.carrosService = carrosService;
	}

	@GetMapping("/listar")
	@Operation(summary = "Listar os carros", description = "Essa rota permite a listagem de todos os carros" +
			" do banco de dados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Carros encontrados com sucesso"),
			@ApiResponse(responseCode = "204", description = "Não há carros na lista")
	})
	public ResponseEntity<?> listarNinjas() {
		List<CarrosDTO> carrosDTO = carrosService.listarCarros();

		if (carrosDTO.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(carrosDTO);
	}

	@GetMapping("/listar/{id}")
	@Operation(summary = "Listar o carro por id", description = "Essa rota permite encontrar carros especificos via ID" +
			" do banco de dados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Carro encontrado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Carro não encontrado")
	})
	public ResponseEntity<?> listarNinjaPorId(
			@Parameter(description = "Usuário fornece o id no caminho da requisição")
			@PathVariable Long id) {
		CarrosDTO carrosDTO = carrosService.listarCarroPorId(id);

		if (carrosDTO == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Carro com o ID " + id + " não encontrado");
		}

		return ResponseEntity.ok(carrosDTO);
	}

	@PutMapping("/alterar/{id}")
	@Operation(summary = "Alterar o carro por id", description = "Essa rota permite alterar carros especificos via ID" +
			" do banco de dados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Carro alterado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Carro não encontrado")
	})
	public ResponseEntity<?> alterarNinjaPorId(
			@Parameter(description = "Usuário fornece o id no caminho da requisição")
			@PathVariable Long id,
			@Parameter(description = "Usuário fornece no corpo da requisição os dados atualizados, no caso de ser um 'PUT'" +
					" é preciso que todos os parametros sejam fornecidos, o que não for fornecido será nulificado")
			@RequestBody CarrosDTO carroAtualizado) {
		CarrosDTO carrosDTO = carrosService.atualizarCarroPorId(id, carroAtualizado);

		if (carrosDTO == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Carro com ID " + id + " nao encontrado");
		}

		return ResponseEntity.ok(carrosDTO);
	}

	@DeleteMapping("/deletar/{id}")
	@Operation(summary = "Deletar o Carro por id", description = "Essa rota permite deletar carros especificos via ID" +
			" do banco de dados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Carro deletado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Carro não encontrado")
	})
	public ResponseEntity<?> deletarNinjaPorId(
			@Parameter(description = "Usuário fornece o id no caminho da requisição")
			@PathVariable Long id) {
		CarrosDTO carrosDTO = carrosService.listarCarroPorId(id);

		if (carrosDTO == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Carro com o ID " + id + " não encontrado");
		}

		carrosService.deletarCarroPorId(id);
		return ResponseEntity.ok("Carro com ID " + id + " deletado com sucesso");
	}

}
