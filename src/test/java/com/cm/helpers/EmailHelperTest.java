package com.cm.helpers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class EmailHelperTest {

    @InjectMocks
    private EmailHelper emailHelper;

    @Mock
    private MailSender mailSender;

    @Test(expected = IllegalArgumentException.class)
    public void whenSendMilInvokedWithoutFromArgThenExceptionIsThrown() {
        emailHelper.sendMail(null, new String[]{"", ""}, "", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSendMilInvokedWithoutToArgThenExceptionIsThrown() {
        emailHelper.sendMail("", null, "", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSendMilInvokedWithoutSubjectArgThenExceptionIsThrown() {
        emailHelper.sendMail("", new String[]{"", ""}, null, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSendMilInvokedWithoutMsgArgThenExceptionIsThrown() {
        emailHelper.sendMail("", new String[]{"", ""}, "", null);
    }

    @Test
    public void whenGetUserByTypeInvokedThenUserRepositoryShouldInvoked() {
        emailHelper.sendMail("", new String[]{"", ""}, "", "");

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
