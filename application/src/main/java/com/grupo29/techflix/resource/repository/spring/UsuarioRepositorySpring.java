package com.grupo29.techflix.resource.repository.spring;

import com.grupo29.techflix.resource.repository.entity.UsuarioEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UsuarioRepositorySpring extends ReactiveCrudRepository<UsuarioEntity, Long> {
}
