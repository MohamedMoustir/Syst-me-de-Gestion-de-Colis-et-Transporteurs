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

}
