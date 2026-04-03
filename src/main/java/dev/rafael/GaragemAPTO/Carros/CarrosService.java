package dev.rafael.GaragemAPTO.Carros;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarrosService {

	private final CarrosRepository carrosRepository;
	private final CarrosMapper carrosMapper;

	public CarrosService(CarrosRepository carrosRepository, CarrosMapper carrosMapper) {
		this.carrosRepository = carrosRepository;
		this.carrosMapper = carrosMapper;
	}

	public List<CarrosDTO> listarCarros() {
		List<CarrosModel> carrosModel = carrosRepository.findAll();
		return carrosModel.stream()
				.map(carrosMapper::map)
				.collect(Collectors.toList());
	}

	public CarrosDTO listarCarroPorId(Long id) {
		Optional<CarrosModel> carrosModel = carrosRepository.findById(id);
		return carrosModel.map(carrosMapper::map)
				.orElse(null);
	}

	public void deletarCarroPorId(Long id) {
		carrosRepository.deleteById(id);
	}

	public CarrosDTO atualizarCarroPorId(Long id, CarrosDTO carrosDTO) {
		Optional<CarrosModel> carrosModel = carrosRepository.findById(id);
		if (carrosModel.isPresent()) {
			CarrosModel carrosAtualizado = carrosMapper.map(carrosDTO);
			carrosAtualizado.setId(id);
			CarrosModel carrosSalvo = carrosRepository.save(carrosAtualizado);
			return carrosMapper.map(carrosSalvo);
		}
		return null;
	}
}
