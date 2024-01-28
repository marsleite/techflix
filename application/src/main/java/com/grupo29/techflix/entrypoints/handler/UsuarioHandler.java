package com.grupo29.techflix.entrypoints.handler;

import com.grupo29.techflix.model.Usuario;
import com.grupo29.techflix.useCase.CreateUsuarioUseCase;
import com.grupo29.techflix.useCase.DeleteUsuarioUseCase;
import com.grupo29.techflix.useCase.FindUsuarioUseCase;
import com.grupo29.techflix.useCase.UpdateUsuarioUseCase;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

@Component
public class UsuarioHandler {

    private final CreateUsuarioUseCase createUsuarioUseCase;

    private final FindUsuarioUseCase findUsuarioUseCase;

    private final UpdateUsuarioUseCase updateUsuarioUseCase;

    private final DeleteUsuarioUseCase deleteUsuarioUseCase;

    public UsuarioHandler(
            CreateUsuarioUseCase createUsuarioUseCase,
            FindUsuarioUseCase findUsuarioUseCase,
            UpdateUsuarioUseCase updateUsuarioUseCase,
            DeleteUsuarioUseCase deleteUsuarioUseCase
    ) {
        this.createUsuarioUseCase = createUsuarioUseCase;
        this.findUsuarioUseCase = findUsuarioUseCase;
        this.updateUsuarioUseCase = updateUsuarioUseCase;
        this.deleteUsuarioUseCase = deleteUsuarioUseCase;
    }

    public Mono<ServerResponse> createUsuario(ServerRequest request) {
        Mono<Usuario> usuarioMono = request.bodyToMono(Usuario.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(usuarioMono.flatMap(createUsuarioUseCase::execute), Usuario.class));
    }

    public Mono<ServerResponse> getUsuario(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(findUsuarioUseCase.execute(id), Usuario.class));
    }

    public Mono<ServerResponse> updateUsuario(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        Mono<Usuario> usuarioMono = request.bodyToMono(Usuario.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(usuarioMono.flatMap(usuario -> updateUsuarioUseCase.execute(usuario, id)), Usuario.class));
    }

    public Mono<ServerResponse> deleteUsuario(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(deleteUsuarioUseCase.execute(id), Void.class));
    }
}
