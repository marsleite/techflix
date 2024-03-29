package com.grupo29.techflix.model;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Video {
  private Long id;

  @Size(min = 2, max = 20, message = "O título deve ter entre 2 e 20 caracteres")
  private String titulo;
  @Size(min = 2, max = 255, message = "A descrição deve ter entre 2 e 255 caracteres")
  private String descricao;
  private String url;
  private Categoria categoria;
  private LocalDateTime dataPublicacao;
  private Integer visualizacoes;
}
