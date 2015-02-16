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
    public void whenSendMilInvokedWithoutFromArg_thenExceptionIsThrown() {
        emailHelper.sendMail(null, new String[]{"", ""}, "", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSendMilInvokedWithoutToArg_thenExceptionIsThrown() {
        emailHelper.sendMail("", null, "", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSendMilInvokedWithoutSubjectArg_thenExceptionIsThrown() {
        emailHelper.sendMail("", new String[]{"", ""}, null, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSendMilInvokedWithoutMsgArg_thenExceptionIsThrown() {
        emailHelper.sendMail("", new String[]{"", ""}, "", null);
    }

    @Test
    public void whenGetUserByTypeInvoked_thenUserRepositoryShouldInvoked() {
        emailHelper.sendMail("", new String[]{"", ""}, "", "");

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}