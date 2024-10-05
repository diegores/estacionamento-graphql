package br.com.estacionamento.infrastructure.controller;

import br.com.estacionamento.application.dto.EstabelecimentoDTO;
import br.com.estacionamento.application.service.EstabelecimentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class EstabelecimentoControllerTest {

    @InjectMocks
    private EstabelecimentoController estabelecimentoController;

    @Mock
    private EstabelecimentoService estabelecimentoService;

    private EstabelecimentoDTO estabelecimentoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        estabelecimentoDTO = new EstabelecimentoDTO();
        estabelecimentoDTO.setId(1L);
        estabelecimentoDTO.setNome("Estabelecimento A");
        estabelecimentoDTO.setCnpj("12.345.678/0001-90");
        estabelecimentoDTO.setEndereco("Rua A, 123");
        estabelecimentoDTO.setTelefone("1234-5678");
        estabelecimentoDTO.setVagasMotos(10);
        estabelecimentoDTO.setVagasCarros(20);
    }

    @Test
    void addCompany_ShouldReturnSavedEstabelecimentoDTO() {
        when(estabelecimentoService.addEstabelecimento(any(EstabelecimentoDTO.class))).thenReturn(estabelecimentoDTO);

        ResponseEntity<EstabelecimentoDTO> response = estabelecimentoController.addCompany(estabelecimentoDTO);

        verify(estabelecimentoService, times(1)).addEstabelecimento(any(EstabelecimentoDTO.class));
        assert response.getStatusCode().is2xxSuccessful();
        assert response.getBody() != null;
        assert response.getBody().getId().equals(estabelecimentoDTO.getId());
    }

    @Test
    void updateCompany_ShouldReturnUpdatedEstabelecimentoDTO() {
        when(estabelecimentoService.updateEstabelecimento(any(EstabelecimentoDTO.class))).thenReturn(estabelecimentoDTO);

        ResponseEntity<EstabelecimentoDTO> response = estabelecimentoController.updateCompany(estabelecimentoDTO);

        verify(estabelecimentoService, times(1)).updateEstabelecimento(any(EstabelecimentoDTO.class));
        assert response.getStatusCode().is2xxSuccessful();
        assert response.getBody() != null;
        assert response.getBody().getId().equals(estabelecimentoDTO.getId());
    }

    @Test
    void listCompanies_ShouldReturnListOfEstabelecimentos() {
        List<EstabelecimentoDTO> estabelecimentoDTOS = new ArrayList<>();
        estabelecimentoDTOS.add(estabelecimentoDTO);
        when(estabelecimentoService.getEstabelecimentos()).thenReturn(estabelecimentoDTOS);

        ResponseEntity<List<EstabelecimentoDTO>> response = estabelecimentoController.listCompanies();

        verify(estabelecimentoService, times(1)).getEstabelecimentos();
        assert response.getStatusCode().is2xxSuccessful();
        assert response.getBody() != null;
        assert response.getBody().size() == 1;
    }

    @Test
    void deleteCompany_ShouldReturn204_WhenDeletedSuccessfully() {
        when(estabelecimentoService.deleteEstabelecimento(anyLong())).thenReturn(true);

        ResponseEntity<Void> response = estabelecimentoController.deleteCompany(1L);

        verify(estabelecimentoService, times(1)).deleteEstabelecimento(1L);
        assert response.getStatusCode() == HttpStatus.NO_CONTENT;
    }

    @Test
    void deleteCompany_ShouldReturn404_WhenNotFound() {
        when(estabelecimentoService.deleteEstabelecimento(anyLong())).thenReturn(false);

        ResponseEntity<Void> response = estabelecimentoController.deleteCompany(1L);

        verify(estabelecimentoService, times(1)).deleteEstabelecimento(1L);
        assert response.getStatusCode() == HttpStatus.NOT_FOUND;
    }
}
