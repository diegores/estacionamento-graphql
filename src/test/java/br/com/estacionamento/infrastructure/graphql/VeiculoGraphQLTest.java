package br.com.estacionamento.infrastructure.graphql;

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

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VeiculoGraphQLTest {

    @InjectMocks
    private VeiculoGraphQL veiculoGraphQL;

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
        veiculoDTO.setModelo("Modelo Teste");

        veiculoInput = new VeiculoInput();
        veiculoInput.setEstabelecimentoId(1L);
        veiculoInput.setModelo("Modelo Teste");

        updateVeiculoInput = new UpdateVeiculoInput();
        updateVeiculoInput.setId(1L);
        updateVeiculoInput.setModelo("Modelo Atualizado");
    }

    @Test
    void getVeiculo_ShouldReturnVeiculo_WhenFound() {
        when(veiculoService.getVeiculo(1L)).thenReturn(Optional.of(veiculoDTO));

        Optional<VeiculoDTO> result = veiculoGraphQL.getVeiculo(1L);

        assertNotNull(result);
        assertEquals(veiculoDTO, result.get());
    }

    @Test
    void getVeiculo_ShouldReturnEmpty_WhenNotFound() {
        when(veiculoService.getVeiculo(1L)).thenReturn(Optional.empty());

        Optional<VeiculoDTO> result = veiculoGraphQL.getVeiculo(1L);

        assertNotNull(result);
        assertFalse(result.isPresent());
    }

    @Test
    void getVeiculos_ShouldReturnListOfVeiculos() {
        when(veiculoService.listVeiculos()).thenReturn(Collections.singletonList(veiculoDTO));

        var result = veiculoGraphQL.getVeiculos();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(veiculoDTO, result.get(0));
    }

    @Test
    void saveVeiculo_ShouldReturnSavedVeiculo() {
        when(veiculoMapper.toDTO(veiculoInput)).thenReturn(veiculoDTO);
        when(veiculoService.addVeiculo(any(VeiculoDTO.class))).thenReturn(veiculoDTO);

        VeiculoDTO result = veiculoGraphQL.saveVeiculo(veiculoInput);

        assertNotNull(result);
        assertEquals(veiculoDTO, result);
        assertEquals(1L, result.getEstabelecimento().getId());
    }

    @Test
    void updateVeiculo_ShouldReturnUpdatedVeiculo() {
        when(veiculoMapper.toDTO(updateVeiculoInput)).thenReturn(veiculoDTO);
        when(veiculoService.updateVeiculo(any(VeiculoDTO.class))).thenReturn(veiculoDTO);

        VeiculoDTO result = veiculoGraphQL.updateVeiculo(updateVeiculoInput);

        assertNotNull(result);
        assertEquals(veiculoDTO, result);
    }

    @Test
    void deleteVeiculo_ShouldCallDeleteVeiculoService() {
        doNothing().when(veiculoService).deleteVeiculo(1L);

        veiculoGraphQL.deleteVeiculo(1L);

        verify(veiculoService, times(1)).deleteVeiculo(1L);
    }
}

