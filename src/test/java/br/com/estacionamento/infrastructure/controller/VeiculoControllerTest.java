package br.com.estacionamento.infrastructure.controller;

import br.com.estacionamento.application.dto.EstabelecimentoDTO;
import br.com.estacionamento.application.dto.UpdateVeiculoInput;
import br.com.estacionamento.application.dto.VeiculoDTO;
import br.com.estacionamento.application.dto.VeiculoInput;
import br.com.estacionamento.application.mapper.VeiculoMapper;
import br.com.estacionamento.application.service.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class VeiculoControllerTest {

    @InjectMocks
    private VeiculoController veiculoController;

    @Mock
    private VeiculoService veiculoService;

    @Mock
    private VeiculoMapper veiculoMapper;

    private VeiculoDTO veiculoDTO;
    private VeiculoInput veiculoInput;
    private UpdateVeiculoInput updateVeiculoInput;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        veiculoDTO = new VeiculoDTO();
        veiculoDTO.setId(1L);
        veiculoDTO.setMarca("Marca");
        veiculoDTO.setModelo("Modelo");
        veiculoDTO.setCor("Cor");
        veiculoDTO.setPlaca("ABC-1234");
        veiculoDTO.setTipo("CARRO");

        veiculoInput = new VeiculoInput();
        veiculoInput.setEstabelecimentoId(1L);

        updateVeiculoInput = new UpdateVeiculoInput();
        updateVeiculoInput.setEstabelecimento(EstabelecimentoDTO.builder().id(1L).build());
    }

    @Test
    void getVeiculo_ShouldReturnVeiculo_WhenFound() {
        when(veiculoService.getVeiculo(1L)).thenReturn(Optional.of(veiculoDTO));

        ResponseEntity<VeiculoDTO> response = veiculoController.getVeiculo(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veiculoDTO, response.getBody());
    }

    @Test
    void getVeiculo_ShouldReturn404_WhenNotFound() {
        when(veiculoService.getVeiculo(1L)).thenReturn(Optional.empty());

        ResponseEntity<VeiculoDTO> response = veiculoController.getVeiculo(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getVeiculos_ShouldReturnListOfVeiculos() {
        when(veiculoService.listVeiculos()).thenReturn(Collections.singletonList(veiculoDTO));

        List<VeiculoDTO> response = veiculoController.getVeiculos();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(veiculoDTO, response.get(0));
    }

    @Test
    void saveVeiculo_ShouldReturn201_WhenSavedSuccessfully() {
        when(veiculoMapper.toDTO(veiculoInput)).thenReturn(veiculoDTO);
        when(veiculoService.addVeiculo(veiculoDTO)).thenReturn(veiculoDTO);

        ResponseEntity<VeiculoDTO> response = veiculoController.saveVeiculo(veiculoInput);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(veiculoDTO, response.getBody());
    }

    @Test
    void updateVeiculo_ShouldReturnUpdatedVeiculo() {
        when(veiculoMapper.toDTO(updateVeiculoInput)).thenReturn(veiculoDTO);
        when(veiculoService.updateVeiculo(veiculoDTO)).thenReturn(veiculoDTO);

        ResponseEntity<VeiculoDTO> response = veiculoController.updateVeiculo(updateVeiculoInput);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veiculoDTO, response.getBody());
    }

    @Test
    void deleteVeiculo_ShouldReturn204_WhenDeletedSuccessfully() {
        doNothing().when(veiculoService).deleteVeiculo(1L);

        ResponseEntity<Void> response = veiculoController.deleteVeiculo(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
