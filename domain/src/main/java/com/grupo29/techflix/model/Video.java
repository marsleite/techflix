package com.grupo29.techflix.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Video {
  private Long id;
  private String titulo;
  private String descricao;
  private String url;
  private String categoria;
  private String dataPublicacao;
}
