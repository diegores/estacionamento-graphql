package br.com.estacionamento.infrastructure.controller;

import br.com.estacionamento.application.dto.EstabelecimentoDTO;
import br.com.estacionamento.application.service.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller responsável por gerenciar as operações relacionadas aos estabelecimentos.
 */
@RestController
@RequestMapping("/api/estabelecimentos")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    /**
     * Adiciona um novo estabelecimento ao sistema.
     *
     * @param estabelecimentoDTO O DTO contendo as informações do estabelecimento a ser adicionado.
     * @return Um ResponseEntity com o DTO do estabelecimento salvo.
     */
    @PostMapping
    public ResponseEntity<EstabelecimentoDTO> addCompany(@RequestBody EstabelecimentoDTO estabelecimentoDTO) {
        EstabelecimentoDTO savedEstabelecimento = estabelecimentoService.addEstabelecimento(estabelecimentoDTO);
        return ResponseEntity.ok(savedEstabelecimento);
    }

    /**
     * Atualiza as informações de um estabelecimento existente.
     *
     * @param estabelecimentoDTO O DTO contendo as informações atualizadas do estabelecimento.
     * @return Um ResponseEntity com o DTO do estabelecimento atualizado.
     */
    @PutMapping
    public ResponseEntity<EstabelecimentoDTO> updateCompany(@RequestBody EstabelecimentoDTO estabelecimentoDTO) {
        EstabelecimentoDTO updateEstabelecimento = estabelecimentoService.updateEstabelecimento(estabelecimentoDTO);
        return ResponseEntity.ok(updateEstabelecimento);
    }

    /**
     * Lista todos os estabelecimentos registrados no sistema.
     *
     * @return Um ResponseEntity contendo uma lista de DTOs dos estabelecimentos.
     */
    @GetMapping
    public ResponseEntity<List<EstabelecimentoDTO>> listCompanies() {
        List<EstabelecimentoDTO> estabelecimentoDTOS = estabelecimentoService.getEstabelecimentos();
        return ResponseEntity.ok(estabelecimentoDTOS);
    }

    /**
     * Deleta um estabelecimento pelo ID.
     *
     * @param id O ID do estabelecimento a ser deletado.
     * @return Um ResponseEntity com status 204 (No Content) se deletado com sucesso, ou 404 (Not Found) se o estabelecimento não for encontrado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        boolean deleted = estabelecimentoService.deleteEstabelecimento(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
