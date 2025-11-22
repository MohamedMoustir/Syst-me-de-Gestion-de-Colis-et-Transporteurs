package User;

import com.example.logistique.dto.UserDTO;
import com.example.logistique.enums.*;
import com.example.logistique.mapper.UserMapper;

import com.example.logistique.model.User;
import com.example.logistique.repository.UserRepository;
import com.example.logistique.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

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
    void test_create_User(){
        User.TransporteurInfo info = new User.TransporteurInfo(
                StatutTransporteur.DISPONIBLE,
                Specialite.STANDARD
        );
        User userEntity = User.builder()
                .login("transporteur99")
                .password("TransPwd99")
                .role(Role.ROLE_TRANSPORTEUR)
                .transporteurInfo( info)
                .build();

        UserDTO userDTO = UserDTO.builder()
                .id("123")
                .login("transporteur99")
                .role(Role.ROLE_TRANSPORTEUR.name())
                .build();

        when(userMapper.toEntity(any(UserDTO.class))).thenReturn(userEntity);
        when(userRepository.save(any(User.class))).thenReturn(userEntity);
        when(userMapper.toDTO(any(User.class))).thenReturn(userDTO);

        UserDTO result = userService.createUser(userDTO);

        verify(userRepository).save(any(User.class));
        assertEquals("transporteur99", result.getLogin());
        assertEquals("123", result.getId());

    }
}
