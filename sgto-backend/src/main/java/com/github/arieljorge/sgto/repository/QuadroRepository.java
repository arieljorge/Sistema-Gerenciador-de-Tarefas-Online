package com.github.arieljorge.sgto.repository;

import com.github.arieljorge.sgto.entity.Quadro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuadroRepository extends JpaRepository<Quadro, Short> {
    @Transactional
    void removeById(Short id);
}
