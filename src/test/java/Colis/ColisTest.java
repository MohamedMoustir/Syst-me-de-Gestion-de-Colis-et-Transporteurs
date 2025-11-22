package Colis;

import com.example.logistique.dto.ColisDTO;
import com.example.logistique.enums.ColisType;
import com.example.logistique.enums.StatutColis;
import com.example.logistique.mapper.ColisMapper;
import com.example.logistique.model.Colis;
import com.example.logistique.repository.ColisRepository;
import com.example.logistique.service.impl.ColisServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;

public class ColisTest {

    @Mock
    private ColisRepository colisRepository ;
    @Mock
    private ColisMapper colisMapper ;

    @InjectMocks
    private ColisServiceImpl colisService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_create_Colis(){
        ColisDTO dto = ColisDTO.builder()
                .id("6920d09deeefe2ec9c9de7a4e8b")
                .type("FRAGILE")
                .poids(2.3)
                .adresseDestination("000000000 Rue Principale, Casablanca")
                .statut("EN_ATTENTE")
                .instructionsManutention("avec précaution")
                .build();

        Colis entity = Colis.builder()
                .id("6920d09deeefe2ec9c9de7a4e8b")
                .type(ColisType.FRAGILE)
                .poids(2.3)
                .adresseDestination("000000000 Rue Principale, Casablanca")
                .statut(StatutColis.EN_ATTENTE)
                .instructionsManutention("avec précaution")
                .build();

        when(colisMapper.toEntity(any())).thenReturn(entity);
        when(colisRepository.save(any())).thenReturn(entity);
        when(colisMapper.toDTO(any())).thenReturn(dto);

        ColisDTO result = colisService.createColis(dto);

        verify(colisRepository, times(1)).save(any());
        assertEquals("6920d09deeefe2ec9c9de7a4e8b", result.getId());
        assertEquals("000000000 Rue Principale, Casablanca", result.getAdresseDestination());

    }

    @Test
    void test_listColis(){

        PageRequest pageable = PageRequest.of(0, 2);

        Colis colis1 = Colis.builder()
                .id("1")
                .type(ColisType.FRAGILE)
                .poids(2.3)
                .adresseDestination("Casa 1")
                .statut(StatutColis.EN_ATTENTE)
                .instructionsManutention("Test").build();

        Colis colis2 = Colis.builder()
                .id("2")
                .type(ColisType.FRAGILE)
                .poids(1.3)
                .adresseDestination("Casa 2")
                .statut(StatutColis.EN_ATTENTE)
                .instructionsManutention("Test").build();

        Page<Colis> page = new PageImpl<>(List.of(colis1, colis2), pageable, 2);

        ColisDTO dto1 = ColisDTO.builder().id("1").type("FRAGILE").adresseDestination("Casa 1").build();
        ColisDTO dto2 = ColisDTO.builder().id("2").type("FRAGILE").adresseDestination("Casa 2").build();

        when(colisRepository.findByType(any(), eq(pageable))).thenReturn(page);
        when(colisMapper.toDTO(colis1)).thenReturn(dto1);
        when(colisMapper.toDTO(colis2)).thenReturn(dto2);

        List<ColisDTO> list =
                colisService.listColis(Optional.of(ColisType.FRAGILE), Optional.of(StatutColis.EN_ATTENTE), 1, 2);

        assertEquals(2, list.size());
        assertEquals("1", list.get(0).getId());
        assertEquals("2", list.get(1).getId());

        verify(colisRepository, times(1)).findByType(any(), eq(pageable));
    }
}
