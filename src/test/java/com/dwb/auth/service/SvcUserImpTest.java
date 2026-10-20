package com.dwb.auth.service;

import com.dwb.auth.dto.in.UserRequest;
import com.dwb.auth.entity.User;
import com.dwb.auth.exception.DuplicateFieldException;
import com.dwb.auth.repo.RepoUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class SvcUserImpTest {

    @Mock private RepoUser repoUser;
    @Mock private PasswordEncoder passwordEncoder;
    @InjectMocks private SvcUserImp svcUserImp;
    @Captor private ArgumentCaptor<User> userCaptor;

    private UserRequest buildUserRequest(){
        UserRequest r = new UserRequest();
        r.setUsername("mdelgado");
        r.setEmail("nuevo@dwb.mx");
        r.setPassword("Secreta123!");
        r.setName("Name");
        r.setLastName("Apellido");
        r.setPhoneNumber("5512345678");
        return r;
    }

    @Test
    void siElUsernameYaExiste_LanzaExcepcionYNoGuarda(){
        when(repoUser.existsByUsername("mdelgado")).thenReturn(true);
        assertThrows(DuplicateFieldException.class, () -> svcUserImp.createUser(buildUserRequest()));
        verify(repoUser, never()).save(any());
    }

    
}
