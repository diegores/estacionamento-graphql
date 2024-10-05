package br.com.estacionamento.application.service;

import br.com.estacionamento.application.dto.EstabelecimentoDTO;
import br.com.estacionamento.application.dto.VeiculoDTO;
import br.com.estacionamento.application.mapper.VeiculoMapper;
import br.com.estacionamento.domain.entity.Estabelecimento;
import br.com.estacionamento.domain.entity.Veiculo;
import br.com.estacionamento.domain.repository.EstabelecimentoRepository;
import br.com.estacionamento.domain.repository.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final EstabelecimentoRepository estabelecimentoRepository;
    private final VeiculoMapper veiculoMapper;

    @Autowired
    public VeiculoService(VeiculoRepository veiculoRepository,
                          EstabelecimentoRepository estabelecimentoRepository,
                          VeiculoMapper veiculoMapper) {
        this.veiculoRepository = veiculoRepository;
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.veiculoMapper = veiculoMapper;
    }

    /**
     * Adiciona um novo veículo ao sistema.
     *
     * @param input O DTO contendo as informações do novo veículo.
     * @return O DTO do veículo salvo.
     */
    public VeiculoDTO addVeiculo(VeiculoDTO input) {
        // Verifica se o tipo de veículo é válido
        String tipoVeiculo = input.getTipo();
        if (!tipoVeiculo.equals("carro") && !tipoVeiculo.equals("moto")) {
            throw new IllegalArgumentException("Tipo de veículo inválido. Apenas 'carro' ou 'moto' são permitidos.");
        }

        // Busca o estabelecimento
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(input.getEstabelecimento().getId())
                .orElseThrow(() -> new EntityNotFoundException("Estabelecimento não encontrado com ID: " + input.getEstabelecimento().getId()));

        // Verifica a disponibilidade de vagas com base no tipo do veículo
        if (tipoVeiculo.equals("moto") && estabelecimento.getVagasMotos() <= 0) {
            throw new IllegalStateException("Não há vagas disponíveis para motos.");
        } else if (tipoVeiculo.equals("carro") && estabelecimento.getVagasCarros() <= 0) {
            throw new IllegalStateException("Não há vagas disponíveis para carros.");
        }

        // Mapeia e salva o veículo
        Veiculo veiculo = veiculoMapper.toEntity(input);
        veiculo.setEstabelecimento(estabelecimento);

        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);

        return toDTO(veiculoSalvo);
    }


    /**
     * Lista todos os veículos no sistema.
     *
     * @return Uma lista de DTOs dos veículos.
     */
    public List<VeiculoDTO> listVeiculos() {
        return veiculoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtém um veículo pelo ID.
     *
     * @param id O ID do veículo a ser obtido.
     * @return Um Optional contendo o DTO do veículo, se encontrado.
     */
    public Optional<VeiculoDTO> getVeiculo(Long id) {
        return veiculoRepository.findById(id)
                .map(this::toDTO);
    }

    /**
     * Atualiza as informações de um veículo existente.
     *
     * @param veiculoDTO O DTO contendo as informações atualizadas do veículo.
     * @return O DTO do veículo atualizado.
     */
    public VeiculoDTO updateVeiculo(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = veiculoRepository.findById(veiculoDTO.getId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado para o ID: " + veiculoDTO.getId()));

        // Se o ID do estabelecimento estiver presente, buscar e definir o estabelecimento
        if (veiculoDTO.getEstabelecimento() != null && veiculoDTO.getEstabelecimento().getId() != null) {
            Estabelecimento estabelecimento = getEstabelecimentoById(veiculoDTO.getEstabelecimento().getId());
            veiculo.setEstabelecimento(estabelecimento);
        }

        // Atualiza os campos do veículo
        veiculo.setMarca(veiculoDTO.getMarca());
        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setCor(veiculoDTO.getCor());
        veiculo.setPlaca(veiculoDTO.getPlaca());
        veiculo.setTipo(veiculoDTO.getTipo());
        veiculo.setEstaEstacionado(veiculoDTO.isEstaEstacionado());

        // Salva a entidade atualizada
        Veiculo updatedVeiculo = veiculoRepository.save(veiculo);
        return toDTO(updatedVeiculo);
    }

    /**
     * Deleta um veículo pelo ID.
     *
     * @param id O ID do veículo a ser deletado.
     */
    public void deleteVeiculo(Long id) {
        if (!veiculoRepository.existsById(id)) {
            throw new EntityNotFoundException("Veículo não encontrado para o ID: " + id);
        }
        veiculoRepository.deleteById(id);
    }

    /**
     * Método auxiliar para buscar e validar o estabelecimento pelo ID.
     *
     * @param id O ID do estabelecimento a ser buscado.
     * @return O estabelecimento encontrado.
     */
    private Estabelecimento getEstabelecimentoById(Long id) {
        return estabelecimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado para o ID: " + id));
    }

    /**
     * Converte um veículo para seu respectivo DTO.
     *
     * @param veiculo O veículo a ser convertido.
     * @return O DTO correspondente ao veículo.
     */
    private VeiculoDTO toDTO(Veiculo veiculo) {
        if (veiculo == null) {
            return null;
        }

        VeiculoDTO dto = new VeiculoDTO();
        dto.setId(veiculo.getId());
        dto.setMarca(veiculo.getMarca());
        dto.setModelo(veiculo.getModelo());
        dto.setCor(veiculo.getCor());
        dto.setPlaca(veiculo.getPlaca());
        dto.setTipo(veiculo.getTipo());
        dto.setEstaEstacionado(veiculo.isEstaEstacionado());

        // Conversão do Estabelecimento para EstabelecimentoDTO, se estiver presente.
        if (veiculo.getEstabelecimento() != null) {
            EstabelecimentoDTO estabelecimentoDTO = getEstabelecimentoDTO(veiculo);
            dto.setEstabelecimento(estabelecimentoDTO);
        }
        return dto;
    }

    /**
     * Obtém o DTO de um estabelecimento a partir do veículo.
     *
     * @param veiculo O veículo a partir do qual o estabelecimento será obtido.
     * @return O DTO do estabelecimento correspondente.
     */
    private static EstabelecimentoDTO getEstabelecimentoDTO(Veiculo veiculo) {
        EstabelecimentoDTO estabelecimentoDTO = new EstabelecimentoDTO();
        estabelecimentoDTO.setId(veiculo.getEstabelecimento().getId());
        estabelecimentoDTO.setNome(veiculo.getEstabelecimento().getNome());
        estabelecimentoDTO.setCnpj(veiculo.getEstabelecimento().getCnpj());
        estabelecimentoDTO.setEndereco(veiculo.getEstabelecimento().getEndereco());
        estabelecimentoDTO.setTelefone(veiculo.getEstabelecimento().getTelefone());
        estabelecimentoDTO.setVagasMotos(veiculo.getEstabelecimento().getVagasMotos());
        estabelecimentoDTO.setVagasCarros(veiculo.getEstabelecimento().getVagasCarros());
        return estabelecimentoDTO;
    }
}