package com.cm.service;

import com.cm.domain.model.Coin;
import com.cm.domain.model.Email;
import com.cm.domain.model.User;
import com.cm.persistence.jpa.EmailRepositoryJPA;
import com.cm.service.impl.EmailServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceImplTest {

    @InjectMocks
    private EmailServiceImpl emailService;

    @Mock
    private EmailRepositoryJPA emailRepository;

    private Coin coin;

    @Before
    public void init() {
        coin = new Coin();
        coin.setCountry("UK");
        coin.setYear(1990);
        coin.setCirculation(23);
        coin.setGrade(Coin.GradeType.EXTRA_FINE);
        coin.setGrade(Coin.GradeType.VERY_FINE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenComposeEmailForClientInvokedWithNullCoin_thenExceptionIsThrown() {
        emailService.composeEmail(null, new LinkedList<>(), User.UserTypes.CLIENT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenComposeEmailForClientInvokedWithNullEmailAdress_thenExceptionIsThrown() {
        emailService.composeEmail(new Coin(), null, User.UserTypes.CLIENT);
    }

//    @Test(expected = IllegalArgumentException.class)
//    public void whenComposeEmailForClientInvokedWithEmptyEmailAdress_thenExceptionIsThrown() {
//        emailService.composeEmail(new Coin(), new LinkedList<>(), User.UserTypes.CLIENT);
//    }

    @Test(expected = IllegalArgumentException.class)
    public void whenComposeEmailForAdminInvokedWithNullCoin_thenExceptionIsThrown() {
        emailService.composeEmail(null, new LinkedList<>(), User.UserTypes.CLIENT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenComposeEmailForAdminInvokedWithNullEmailAdress_thenExceptionIsThrown() {
        emailService.composeEmail(new Coin(), null, User.UserTypes.CLIENT);
    }

//    @Test(expected = IllegalArgumentException.class)
//    public void whenComposeEmailForAdminInvokedWithEmptyEmailAdress_thenExceptionIsThrown() {
//        emailService.composeEmail(new Coin(), new LinkedList<>(), User.UserTypes.CLIENT);
//    }

//    @Test
//    public void whenComposeEmailForAdminInvoked_thenEmailShouldBeReturnedWithSameEmailAdrressAsInArg() {
//        Email email = emailService.composeEmail(this.coin, new LinkedList<>(), User.UserTypes.ADMIN);
//
//        assertEquals("qqq@qqq.qqq", email.getTo());
//    }

//    @Test
//    public void whenComposeEmailForClientInvoked_thenEmailShouldBeReturnedWithSameEmailAdrressAsInArg() {
//        Email email = emailService.composeEmail(this.coin, new LinkedList<>(), User.UserTypes.CLIENT);
//
//        assertEquals("qqq@qqq.qqq", email.getTo());
//    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSaveEmailInvokedWithNullArg_thenExceptionIsThrown() {
        emailService.saveEmail(null);
    }

    @Test
    public void whenSaveEmailInvoked_thenEmailRepositoryShouldInvoked() {
        Email email = new Email();

        emailService.saveEmail(new Email());

        verify(emailRepository, times(1)).create(any(Email.class));
    }
}
