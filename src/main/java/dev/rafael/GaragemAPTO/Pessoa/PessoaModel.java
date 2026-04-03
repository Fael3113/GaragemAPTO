package dev.rafael.GaragemAPTO.Pessoa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.rafael.GaragemAPTO.Carros.CarrosModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tb_pessoas")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = "carro")
public class PessoaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Column(unique = true)
	private String cpf;

	@Column(unique = true)
	private String email;

	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "carro_id")
	private CarrosModel carro;
}
