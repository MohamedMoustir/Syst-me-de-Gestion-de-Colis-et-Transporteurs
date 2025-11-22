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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        ColisDTO colis = ColisDTO.builder()
                .id("6920d09deeefe2ec9c9de7a4e8b")
                .type(String.valueOf(ColisType.FRAGILE))
                .poids(2.3)
                .adresseDestination("000000000 Rue Principale, Casablanca")
                .statut(String.valueOf(StatutColis.EN_ATTENTE))
                .instructionsManutention("avec précaution").build();

              Colis colis1 = colisMapper.toEntity(colis);

                  when(colisRepository.save(Mockito.any(Colis.class))).thenReturn(colis1);
                  ColisDTO result = colisService.createColis(colis);
                 verify(colisRepository , Mockito.times(1)).save(Mockito.any(Colis.class));
                   assertEquals("6920d09deeefe2ec9c9de7a4e8b" ,result.getId());
                   assertEquals("000000000 Rue Principale, Casablanca",result.getAdresseDestination());

    }

    @Test
    void test_listColis(){
        PageRequest pageable = PageRequest.of(0, 2);

        Colis colisEntity1 = Colis.builder()
                .id("1")
                .type(ColisType.FRAGILE)
                .poids(2.3)
                .adresseDestination("Casa 1")
                .statut(StatutColis.EN_ATTENTE)
                .instructionsManutention("Test").build();

        Colis colisEntity2 = Colis.builder()
                .id("2")
                .type(ColisType.FRAGILE)
                .poids(1.3)
                .adresseDestination("Casa 2")
                .statut(StatutColis.EN_ATTENTE)
                .instructionsManutention("Test").build();

        List<Colis> colisList = List.of(colisEntity1, colisEntity2);
        Page<Colis> pageColis = new PageImpl<>(colisList, pageable, colisList.size());
        when(colisRepository.findByType(any(),eq(pageable))).thenReturn(pageColis);
        when(colisMapper.toDTO(colisEntity1));
        when(colisMapper.toDTO(colisEntity2));
        List<ColisDTO> colisDTOList = colisService.listColis(Optional.of(ColisType.FRAGILE), Optional.of(StatutColis.EN_ATTENTE),1,2);
        assertEquals(colisDTOList.size(),2);
        assertEquals("1",colisDTOList.get(0).getId());
        assertEquals("2",colisDTOList.get(1).getId());
        verify(colisRepository ,times(1)).findByType(any(),pageable);
    }
}
