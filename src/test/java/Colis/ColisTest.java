package Colis;

import com.example.logistique.dto.ColisDTO;
import com.example.logistique.enums.ColisType;
import com.example.logistique.enums.StatutColis;
import com.example.logistique.model.Colis;
import com.example.logistique.repository.ColisRepository;
import com.example.logistique.service.impl.ColisServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ColisTest {

    @Mock
    private ColisRepository colisRepository;

    @InjectMocks
    private ColisServiceImpl colisService;

    private Colis colis;
    private ColisDTO colisDTO;

    @BeforeEach
    void setUp() {
        colis = Colis.builder()
                .id("123")
                .type(ColisType.STANDARD)
                .statut(StatutColis.EN_ATTENTE)
                .poids(10.5)
                .adresseDestination("Paris")
                .build();

        colisDTO = ColisDTO.builder()
                .type("STANDARD")
                .poids(10.5)
                .adresseDestination("Paris")
                .build();
    }

    @Test
    void createColis_ShouldReturnSavedColisDTO() {
        when(colisRepository.save(any(Colis.class))).thenReturn(colis);

        ColisDTO result = colisService.createColis(colisDTO);

        assertThat(result).isNotNull();
        assertThat(result.getAdresseDestination()).isEqualTo("Paris");
        verify(colisRepository, times(1)).save(any(Colis.class));
    }


    @Test
    void updateColis_ShouldUpdateAndReturnDTO_WhenColisExists() {
        String id = "123";

        ColisDTO updateInfo = ColisDTO.builder()
                .adresseDestination("Lyon")
                .poids(20.0)
                .type("FRAGILE")
                .build();

        when(colisRepository.findById(id)).thenReturn(Optional.of(colis));

        when(colisRepository.save(any(Colis.class))).thenAnswer(invocation -> {
            Colis savedColis = invocation.getArgument(0);
            return savedColis;
        });

        ColisDTO result = colisService.updateColis(id, updateInfo);

        assertThat(result.getAdresseDestination()).isEqualTo("Lyon");
        assertThat(result.getPoids()).isEqualTo(20.0);
        verify(colisRepository).save(any(Colis.class));
    }

    @Test
    void updateColis_ShouldThrowException_WhenColisNotFound() {
        String id = "999";
        when(colisRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> colisService.updateColis(id, colisDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Colis not found");

        verify(colisRepository, never()).save(any());
    }

    @Test
    void deleteColis_ShouldCallRepositoryDelete() {

        colisService.deleteColis("123");


        verify(colisRepository, times(1)).deleteById("123");
    }

    @Test
    void updateStatut_ShouldUpdateStatut() {
        when(colisRepository.findById("123")).thenReturn(Optional.of(colis));
        when(colisRepository.save(any(Colis.class))).thenReturn(colis);


        colisService.updateStatut("123", StatutColis.LIVRE);


        assertThat(colis.getStatut()).isEqualTo(StatutColis.LIVRE);
        verify(colisRepository).save(colis);
    }

    @Test
    void assignTransporteur_ShouldSetTransporteurId() {
        String transporteurId = "trans-007";
        when(colisRepository.findById("123")).thenReturn(Optional.of(colis));
        when(colisRepository.save(any(Colis.class))).thenReturn(colis);

        colisService.assignTransporteur("123", transporteurId);

        assertThat(colis.getTransporteurId()).isEqualTo(transporteurId);
        verify(colisRepository).save(colis);
    }


    @Test
    void listColis_ShouldReturnAll_WhenNoFilters() {
        Page<Colis> pageColis = new PageImpl<>(List.of(colis));
        when(colisRepository.findAll(any(Pageable.class))).thenReturn(pageColis);

        List<ColisDTO> result = colisService.listColis(Optional.empty(), Optional.empty(), 0, 10);


        assertThat(result).hasSize(1);
        verify(colisRepository).findAll(any(Pageable.class));
    }

    @Test
    void listColis_ShouldFilterByTypeAndStatut() {

        Colis fragileColis = Colis.builder()
                .id("123")
                .type(ColisType.FRAGILE)
                .statut(StatutColis.EN_ATTENTE)
                .build();

        Page<Colis> pageColis = new PageImpl<>(List.of(fragileColis));

        when(colisRepository.findByType(eq(ColisType.FRAGILE), any(Pageable.class)))
                .thenReturn(pageColis);

        List<ColisDTO> result = colisService.listColis(
                Optional.of(ColisType.FRAGILE),
                Optional.of(StatutColis.EN_ATTENTE),
                0, 10
        );

        assertThat(result).hasSize(1);
    }

    @Test
    void listColis_ShouldReturnEmpty_WhenTypeMatchesButStatutDoesNot() {
        Colis fragileLivre = Colis.builder()
                .type(ColisType.FRAGILE)
                .statut(StatutColis.LIVRE)
                .build();

        Page<Colis> pageColis = new PageImpl<>(List.of(fragileLivre));

        when(colisRepository.findByType(eq(ColisType.FRAGILE), any(Pageable.class)))
                .thenReturn(pageColis);


        List<ColisDTO> result = colisService.listColis(
                Optional.of(ColisType.FRAGILE),
                Optional.of(StatutColis.EN_ATTENTE),
                0, 10
        );


        assertThat(result).isEmpty();
    }


}