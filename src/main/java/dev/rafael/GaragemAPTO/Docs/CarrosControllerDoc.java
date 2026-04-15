package dev.rafael.GaragemAPTO.Docs;

import dev.rafael.GaragemAPTO.Carros.CarrosDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
	name = "Carros",
	description = "Endpoints responsáveis por listar, alterar e deletar carros"
)
public interface CarrosControllerDoc {

	@Operation(
		summary = "Listar os carros",
		description = "Essa rota permite a listagem de todos os carros do banco de dados")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Carros encontrados com sucesso"),
		@ApiResponse(responseCode = "204", description = "Não há carros na lista")
	})
	ResponseEntity<Object> listarCarros();

	@Operation(
		summary = "Listar o carro por id",
		description = "Essa rota permite encontrar carros especificos via ID do banco de dados")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Carro encontrado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Carro não encontrado")
	})
	ResponseEntity<Object> listarCarroPorId(
		@Parameter(description = "Usuário fornece o id no caminho da requisição")
		Long id);

	@Operation(
		summary = "Alterar o carro por id",
		description = "Essa rota permite alterar carros especificos via ID do banco de dados")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Carro alterado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Carro não encontrado")
	})
	ResponseEntity<Object> alterarCarroPorId(
		@Parameter(description = "Usuário fornece o id no caminho da requisição")
		Long id,
		@Parameter(description = "Usuário fornece no corpo da requisição os dados atualizados, no caso de ser um 'PUT'" +
				" é preciso que todos os parametros sejam fornecidos, o que não for fornecido será nulificado")
		@RequestBody CarrosDTO carroAtualizado);

	@Operation(
		summary = "Deletar o Carro por id",
		description = "Essa rota permite deletar carros especificos via ID do banco de dados")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Carro deletado com sucesso"),
		@ApiResponse(responseCode = "404", description = "Carro não encontrado")
	})
	ResponseEntity<Object> deletarCarroPorId(
		@Parameter(description = "Usuário fornece o id no caminho da requisição")
		Long id);
}
