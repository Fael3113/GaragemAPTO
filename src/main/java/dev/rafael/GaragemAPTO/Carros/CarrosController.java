package dev.rafael.GaragemAPTO.Carros;

import dev.rafael.GaragemAPTO.Docs.CarrosControllerDoc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarrosController implements CarrosControllerDoc {

	private final CarrosService carrosService;

	public CarrosController(CarrosService carrosService) {
		this.carrosService = carrosService;
	}

	@GetMapping("/listar")
	public ResponseEntity<Object> listarCarros() {
		List<CarrosDTO> carrosDTO = carrosService.listarCarros();

		if (carrosDTO.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(carrosDTO);
	}

	@GetMapping("/listar/{id}")
	public ResponseEntity<Object> listarCarroPorId(@PathVariable Long id) {
		CarrosDTO carrosDTO = carrosService.listarCarroPorId(id);

		if (carrosDTO == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Carro com o ID " + id + " não encontrado");
		}

		return ResponseEntity.ok(carrosDTO);
	}

	@PutMapping("/alterar/{id}")
	public ResponseEntity<Object> alterarCarroPorId(@PathVariable Long id, @RequestBody CarrosDTO carroAtualizado) {
		CarrosDTO carrosDTO = carrosService.atualizarCarroPorId(id, carroAtualizado);

		if (carrosDTO == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Carro com ID " + id + " nao encontrado");
		}

		return ResponseEntity.ok(carrosDTO);
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Object> deletarCarroPorId(@PathVariable Long id) {
		CarrosDTO carrosDTO = carrosService.listarCarroPorId(id);

		if (carrosDTO == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Carro com o ID " + id + " não encontrado");
		}

		carrosService.deletarCarroPorId(id);
		return ResponseEntity.ok("Carro com ID " + id + " deletado com sucesso");
	}

}
