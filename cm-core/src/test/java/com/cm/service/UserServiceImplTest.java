package com.cm.service;

import com.cm.domain.model.User;
import com.cm.persistence.jpa.UserRepositoryJPA;
import com.cm.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepositoryJPA userRepository;

    @Test(expected = IllegalArgumentException.class)
    public void whenGetUserByTypeInvokedWithNullArg_thenExceptionIsThrown() {
        userService.getUsersByType(null);
    }

    @Test
    public void whenGetUserByTypeInvoked_thenUserRepositoryShouldInvoked() {
        userService.getUsersByType(User.UserTypes.ADMIN);

        verify(userRepository, times(1)).findByType(any(User.UserTypes.class));
    }
}
