package br.com.estacionamento.infrastructure.controller;

import br.com.estacionamento.application.dto.EstabelecimentoDTO;
import br.com.estacionamento.application.dto.UpdateVeiculoInput;
import br.com.estacionamento.application.dto.VeiculoDTO;
import br.com.estacionamento.application.dto.VeiculoInput;
import br.com.estacionamento.application.mapper.VeiculoMapper;
import br.com.estacionamento.application.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    private final VeiculoService veiculoService;
    private final VeiculoMapper veiculoMapper;

    @Autowired
    public VeiculoController(VeiculoService veiculoService,
                             VeiculoMapper veiculoMapper) {
        this.veiculoService = veiculoService;
        this.veiculoMapper = veiculoMapper;
    }

    /**
     * Obtém um veículo pelo ID.
     *
     * @param id O ID do veículo.
     * @return Um ResponseEntity com o veículo ou not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> getVeiculo(@PathVariable Long id) {
        Optional<VeiculoDTO> veiculoDTO = veiculoService.getVeiculo(id);
        return veiculoDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Obtém todos os veículos.
     *
     * @return Uma lista de DTOs de veículos.
     */
    @GetMapping
    public List<VeiculoDTO> getVeiculos() {
        return veiculoService.listVeiculos();
    }

    /**
     * Adiciona um novo veículo.
     *
     * @param input O DTO do veículo a ser adicionado.
     * @return Um ResponseEntity com o veículo salvo e status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<VeiculoDTO> saveVeiculo(@RequestBody VeiculoInput input) {
        VeiculoDTO veiculoDTO = veiculoMapper.toDTO(input);

        EstabelecimentoDTO estabelecimentoDTO = new EstabelecimentoDTO();
        estabelecimentoDTO.setId(input.getEstabelecimentoId());
        veiculoDTO.setEstabelecimento(estabelecimentoDTO);

        VeiculoDTO savedVeiculo = veiculoService.addVeiculo(veiculoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVeiculo);
    }

    /**
     * Atualiza um veículo existente.
     *
     * @param input O DTO com as informações do veículo a ser atualizado.
     * @return Um ResponseEntity com o veículo atualizado.
     */
    @PutMapping
    public ResponseEntity<VeiculoDTO> updateVeiculo(@RequestBody UpdateVeiculoInput input) {
        VeiculoDTO veiculoDTO = veiculoMapper.toDTO(input);

        if (input.getEstabelecimento() != null) {
            EstabelecimentoDTO estabelecimentoDTO = new EstabelecimentoDTO();
            estabelecimentoDTO.setId(input.getEstabelecimento().getId());
            veiculoDTO.setEstabelecimento(estabelecimentoDTO);
        }

        VeiculoDTO updatedVeiculo = veiculoService.updateVeiculo(veiculoDTO);
        return ResponseEntity.ok(updatedVeiculo);
    }

    /**
     * Deleta um veículo pelo ID.
     *
     * @param id O ID do veículo a ser deletado.
     * @return Um ResponseEntity com status 204 (No Content).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable Long id) {
        veiculoService.deleteVeiculo(id);
        return ResponseEntity.noContent().build();
    }
}