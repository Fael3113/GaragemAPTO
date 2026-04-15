package dev.rafael.GaragemAPTO.Docs;

import dev.rafael.GaragemAPTO.Pessoa.PessoaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(
	name = "Pessoas",
	description = "Endpoints responsáveis por criar, listar, alterar e deletar pessoas"
)
public interface PessoaControllerDoc {

	@Operation(
		summary = "Cria nova pessoa",
		description = "Essa rota permite a criação de novas pessoas e insere no banco de dados")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso"),
		@ApiResponse(responseCode = "400", description = "Erro na criação da pessoa")
	})
	ResponseEntity<Object> criarPessoa(
		@Parameter(description = "Usuário fornece no corpo da requisição os dados para criação, " +
				"é preciso que todos os parametros sejam fornecidos, o que não for fornecido será nulificado")
		@RequestBody PessoaDTO pessoa);

	@Operation(
		summary = "Listar as pessoas",
		description = "Essa rota permite a listagem de todos as pessoas do banco de dados")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Pessoas encontradas com sucesso"),
		@ApiResponse(responseCode = "204", description = "Não há pessoas na lista")
	})
	ResponseEntity<Object> listarPessoas();

	@Operation(
		summary = "Listar a pessoa por id",
		description = "Essa rota permite encontrar pessoas especificas via ID do banco de dados")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Pessoa encontrada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
	})
	ResponseEntity<Object> listarPessoaPorId(
		@Parameter(description = "Usuário fornece o id no caminho da requisição")
		Long id);

	@Operation(
		summary = "Alterar a pessoa por id",
		description = "Essa rota permite alterar pessoas especificas via ID do banco de dados")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Pessoa alterada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
	})
	ResponseEntity<Object> alterarPessoaPorId(
		@Parameter(description = "Usuário fornece o id no caminho da requisição")
		Long id,
		@Parameter(description = "Usuário fornece no corpo da requisição os dados atualizados, no caso de ser um 'PUT'" +
				" é preciso que todos os parametros sejam fornecidos, o que não for fornecido será nulificado")
		@RequestBody PessoaDTO pessoaAtualizada);

	@Operation(
		summary = "Deletar a pessoa por id",
		description = "Essa rota permite deletar pessoas especificas via ID do banco de dados")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Pessoa deletada com sucesso"),
		@ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
	})
	ResponseEntity<Object> deletarPessoaPorId(
		@Parameter(description = "Usuário fornece o id no caminho da requisição")
        Long id);
}
