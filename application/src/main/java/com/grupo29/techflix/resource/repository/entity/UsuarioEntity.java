package com.grupo29.techflix.resource.repository.entity;

import com.grupo29.techflix.model.Usuario;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Table("usuario")
public class UsuarioEntity {
  @Id
  private Long id;
  private String nome;
  private String email;

  public Usuario toDomain() {
    return Usuario.builder()
        .id(this.id)
        .nome(this.nome)
        .email(this.email)
        .build();
  }

  public static UsuarioEntity fromDomain(Usuario usuario) {
    return UsuarioEntity.builder()
        .id(usuario.getId())
        .nome(usuario.getNome())
        .email(usuario.getEmail())
        .build();
  }
}
