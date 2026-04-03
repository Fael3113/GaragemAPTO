package dev.rafael.GaragemAPTO.Carros;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.rafael.GaragemAPTO.Pessoa.PessoaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tb_carros")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CarrosModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String nome;
	private String marca;
	private String placa;

	@OneToOne (mappedBy = "carro")
	@JsonIgnore
	private PessoaModel pessoa;

}
