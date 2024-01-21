package com.grupo29.techflix.resource.repository.entity;

import com.grupo29.techflix.model.Favorito;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Table("favorito")
public class FavoritoEntity {
  @Id
  private Long id;
  private Long idUsuario;
  private Long idVideo;

  public Favorito toDomain() {
    return Favorito.builder()
        .id(this.id)
        .idUsuario(this.idUsuario)
        .idVideo(this.idVideo)
        .build();
  }

  public static FavoritoEntity fromDomain(Favorito favorito) {
    return FavoritoEntity.builder()
        .id(favorito.getId())
        .idUsuario(favorito.getIdUsuario())
        .idVideo(favorito.getIdVideo())
        .build();
  }
}
