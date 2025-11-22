package User;

import com.example.logistique.dto.ColisDTO;
import com.example.logistique.dto.UserDTO;
import com.example.logistique.enums.*;
import com.example.logistique.mapper.UserMapper;
import com.example.logistique.model.Colis;
import com.example.logistique.model.User;
import com.example.logistique.repository.UserRepository;
import com.example.logistique.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserTest {

    @Mock
    private UserRepository userRepository ;
    @Mock
    private UserMapper userMapper ;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_create_Colis(){
        User.TransporteurInfo info = new User.TransporteurInfo(
                StatutTransporteur.DISPONIBLE,
                Specialite.STANDARD
        );
        User user = User.builder()
                .login("transporteur99")
                .password("TransPwd99")
                .role(Role.ROLE_TRANSPORTEUR)
                .transporteurInfo( info)
                .build();

        UserDTO colis1 = userMapper.toDTO(userRepository.save(user));

         userService.createUser(colis1);
        verify(userRepository , Mockito.times(1)).save(Mockito.any(User.class));

    }
}
