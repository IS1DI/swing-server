package org.aist.swing_server.service;

import lombok.RequiredArgsConstructor;
import org.aist.swing_server.domain.Type;
import org.aist.swing_server.repos.TypeRepository;
import org.aist.swing_server.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class TypeService {
    private final TypeRepository typeRepository;

    public List<Type> findAll() {
        return typeRepository.findAll(Sort.by("type"));
    }

    public Type get(final String type) {
        return typeRepository.findById(type)
                .orElseThrow(NotFoundException::new);
    }

    public Type create(Type type) {
        return typeRepository.save(type);
    }

    public void delete(final String type) {
        typeRepository.deleteById(type);
    }

    public boolean typeExists(final String type) {
        return typeRepository.existsByTypeIgnoreCase(type);
    }

}
