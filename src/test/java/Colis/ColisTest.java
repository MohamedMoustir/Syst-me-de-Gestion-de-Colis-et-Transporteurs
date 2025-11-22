package Colis;

import com.example.logistique.dto.ColisDTO;
import com.example.logistique.enums.ColisType;
import com.example.logistique.enums.StatutColis;
import com.example.logistique.mapper.ColisMapper;
import com.example.logistique.model.Colis;
import com.example.logistique.repository.ColisRepository;
import com.example.logistique.service.impl.ColisServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ColisTest {

    @Mock
    private ColisRepository colisRepository;
    @Mock
    private ColisMapper colisMapper;

    @InjectMocks
    private ColisServiceImpl colisService;

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
//    @Test
//    void test_listColis() {
//
//        PageRequest pageable = PageRequest.of(1, 2);
//
//        Colis colis1 = Colis.builder()
//                .id("1")
//                .type(ColisType.FRAGILE)
//                .poids(2.3)
//                .adresseDestination("Casa 1")
//                .statut(StatutColis.EN_ATTENTE)
//                .instructionsManutention("Test").build();
//
//        Colis colis2 = Colis.builder()
//                .id("2")
//                .type(ColisType.FRAGILE)
//                .poids(1.3)
//                .adresseDestination("Casa 2")
//                .statut(StatutColis.EN_ATTENTE)
//                .instructionsManutention("Test").build();
//
//        Page<Colis> page = new PageImpl<>(List.of(colis1, colis2), pageable, 2);
//
//        ColisDTO dto1 = ColisDTO.builder().id("1").type("FRAGILE").adresseDestination("Casa 1").build();
//        ColisDTO dto2 = ColisDTO.builder().id("2").type("FRAGILE").adresseDestination("Casa 2").build();
//
//
//        when(colisRepository.findByType(any(ColisType.class), any(Pageable.class))).thenReturn(page);
//
//        when(colisMapper.toDTO(colis1)).thenReturn(dto1);
//        when(colisMapper.toDTO(colis2)).thenReturn(dto2);
//
//        List<ColisDTO> list = colisService.listColis(
//                Optional.of(ColisType.FRAGILE),
//                Optional.of(StatutColis.EN_ATTENTE),
//                1,
//                2
//        );
//
//        assertEquals(2, list.size());
//        assertEquals("1", list.get(0).getId());
//        assertEquals("2", list.get(1).getId());
//
//        verify(colisRepository, times(1)).findByType(any(ColisType.class), any(Pageable.class));
//    }
}