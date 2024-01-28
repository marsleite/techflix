package com.grupo29.techflix.useCase;

import com.grupo29.techflix.gateway.FavoritoRepositoryGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RemoveFavoritoTest {

    @Mock
    private FavoritoRepositoryGateway favoritoRepositoryGateway;

    @InjectMocks
    private RemoveFavoritoUseCase removerFavorito;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should remove a favorite when user and video exist")
    public void shouldRemoveFavoriteWhenUserAndVideoExist() {
        doNothing().when(favoritoRepositoryGateway).removerFavorito(anyLong(), anyLong());

        removerFavorito.execute(1L, 1L);

        verify(favoritoRepositoryGateway, times(1)).removerFavorito(anyLong(), anyLong());
    }
}
