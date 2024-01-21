package com.grupo29.techflix.resource.repository.spring;

import com.grupo29.techflix.resource.repository.entity.FavoritoEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface FavoritoRepositorySpring extends ReactiveCrudRepository<FavoritoEntity, Long> {
}
