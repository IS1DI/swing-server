package org.aist.swing_server.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aist.swing_server.mapper.TypeMapper;
import org.aist.swing_server.model.TypeDto;
import org.aist.swing_server.service.TypeService;
import org.springframework.core.MethodParameter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/types")
@RequiredArgsConstructor
public class TypeController {
    private final TypeService typeService;
    private final TypeMapper typeMapper;

    @GetMapping
    public List<TypeDto> getAllTypes() {
        return typeService.findAll().stream().map(typeMapper::toOutput).collect(Collectors.toList());
    }

    @GetMapping("/{type}")
    public TypeDto getType(@PathVariable String type) {
        return typeMapper.toOutput(typeService.get(type));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public TypeDto createType(@RequestBody @Valid final TypeDto typeDTO,
            final BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (!bindingResult.hasFieldErrors("type") && typeDTO.getType() == null) {
            bindingResult.rejectValue("type", "NotNull");
        }
        if (!bindingResult.hasFieldErrors("type") && typeService.typeExists(typeDTO.getType())) {
            bindingResult.rejectValue("type", "Exists.type.type");
        }
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(new MethodParameter(
                    this.getClass().getDeclaredMethods()[0], -1), bindingResult);
        }
        return typeMapper.toOutput(typeService.create(typeMapper.toEntity(typeDTO)));
    }

    @DeleteMapping("/{type}")
    public void deleteType(@PathVariable(name = "type") final String type) {
        typeService.delete(type);
    }

}
