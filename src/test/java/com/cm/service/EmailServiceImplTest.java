package com.cm.service;

import com.cm.domain.model.Coin;
import com.cm.domain.model.Email;
import com.cm.domain.model.User;
import com.cm.helpers.factory.mail.EmailComposer;
import com.cm.helpers.factory.mail.EmailComposerFactory;
import com.cm.persistence.jpa.EmailRepositoryJPA;
import com.cm.service.impl.EmailServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceImplTest {

    @InjectMocks
    private EmailServiceImpl emailService;

    @Mock
    private EmailRepositoryJPA emailRepository;

    @Mock
    private EmailComposerFactory composerFactory;

    @Mock
    private EmailComposer emailComposer;

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
    public void whenComposeEmailForClientInvokedWithNullCoinThenExceptionIsThrown() {
        emailService.composeEmail(null, new LinkedList<>(), User.UserTypes.CLIENT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenComposeEmailForClientInvokedWithNullEmailAdressThenExceptionIsThrown() {
        emailService.composeEmail(new Coin(), null, User.UserTypes.CLIENT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenComposeEmailForAdminInvokedWithNullCoinThenExceptionIsThrown() {
        emailService.composeEmail(null, new LinkedList<>(), User.UserTypes.CLIENT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenComposeEmailForAdminInvokedWithNullEmailAdressThenExceptionIsThrown() {
        emailService.composeEmail(new Coin(), null, User.UserTypes.CLIENT);
    }

    @Test
    public void whenComposeEmailForAdminInvokedThenComposeEmailShouldBeCalled() {
        List<String> emails = new ArrayList<>(1);
        emails.add("qqq@qqq.qqq");

        when(composerFactory.getForType(User.UserTypes.ADMIN.toString())).thenReturn(this.emailComposer);

        Email email = emailService.composeEmail(this.coin, emails, User.UserTypes.ADMIN);

        verify(emailComposer, times(1)).composeEmail(coin, emails);
    }

    @Test
    public void whenComposeEmailForClientInvokedThenComposeEmailShouldBeCalled() {
        List<String> emails = new ArrayList<>(1);
        emails.add("qqq@qqq.qqq");

        when(composerFactory.getForType(User.UserTypes.CLIENT.toString())).thenReturn(this.emailComposer);

        Email email = emailService.composeEmail(this.coin, emails, User.UserTypes.CLIENT);

        verify(emailComposer, times(1)).composeEmail(coin, emails);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSaveEmailInvokedWithNullArgThenExceptionIsThrown() {
        emailService.saveEmail(null);
    }

    @Test
    public void whenSaveEmailInvoked_thenEmailRepositoryShouldInvoked() {
        Email email = new Email();

        emailService.saveEmail(new Email());

        verify(emailRepository, times(1)).create(any(Email.class));
    }
}
