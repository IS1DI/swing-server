package org.aist.swing_server.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aist.swing_server.mapper.JewelryMapper;
import org.aist.swing_server.model.JewelryDto;
import org.aist.swing_server.service.JewelryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/jewelries")
@RequiredArgsConstructor
public class JewelryController {
    private final JewelryService jewelryService;
    private final JewelryMapper jewelryMapper;

    @GetMapping
    public List<JewelryDto.Output> getAllJewelries() {
        return jewelryService.findAll().stream().map(jewelryMapper::toOutput).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public JewelryDto.Output getJewelry(@PathVariable UUID id) {
        return jewelryMapper.toOutput(jewelryService.get(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public JewelryDto.Output createJewelry(@RequestBody @Valid JewelryDto.Create jewelryDTO) {
        return jewelryMapper.toOutput(jewelryService.create(jewelryMapper.toEntity(jewelryDTO)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public JewelryDto.Output updateJewelry(@PathVariable UUID id,
            @RequestBody @Valid final JewelryDto.Update jewelryDTO) {
        return jewelryMapper.toOutput(jewelryService.update(id, jewelryDTO, jewelryMapper::toUpdate));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteJewelry(@PathVariable UUID id) {
        jewelryService.delete(id);
    }
}
