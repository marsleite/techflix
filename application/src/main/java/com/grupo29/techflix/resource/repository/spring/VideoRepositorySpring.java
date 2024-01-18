package com.grupo29.techflix.resource.repository.spring;

import com.grupo29.techflix.resource.repository.entity.VideoEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface VideoRepositorySpring extends ReactiveCrudRepository<VideoEntity, Long> {
}
