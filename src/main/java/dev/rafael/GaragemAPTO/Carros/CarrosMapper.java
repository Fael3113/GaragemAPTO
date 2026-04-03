package dev.rafael.GaragemAPTO.Carros;

import org.springframework.stereotype.Component;

@Component
public class CarrosMapper {

	public CarrosModel map(CarrosDTO carrosDTO) {
		CarrosModel carrosModel = new CarrosModel();
		carrosModel.setId(carrosDTO.getId());
		carrosModel.setNome(carrosDTO.getNome());
		carrosModel.setMarca(carrosDTO.getMarca());
		carrosModel.setPlaca(carrosDTO.getPlaca());
		carrosModel.setPessoa(carrosDTO.getPessoa());

		return carrosModel;
	}

	public CarrosDTO map(CarrosModel carrosModel) {
		CarrosDTO carrosDTO = new CarrosDTO();
		carrosDTO.setId(carrosModel.getId());
		carrosDTO.setNome(carrosModel.getNome());
		carrosDTO.setMarca(carrosModel.getMarca());
		carrosDTO.setPlaca(carrosModel.getPlaca());
		carrosDTO.setPessoa(carrosModel.getPessoa());

		return carrosDTO;
	}
}
