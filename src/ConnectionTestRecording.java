import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class ConnectionTestRecording {

    Mailbox currentMailbox;
    MailSystem mailSystem;
    Telephone phone;
    Connection connection;

    private static String MAILBOX_MENU_TEXT = "Enter 1 to listen to your messages\n"
            + "Enter 2 to change your passcode\n"
            + "Enter 3 to change your greeting";

    @Before
    public void setup() {
        currentMailbox = mock(Mailbox.class);
        mailSystem = mock(MailSystem.class);
        phone = mock(Telephone.class);
        connection = new Connection(mailSystem, phone);
    }

    @Test
    public void inLoginShouldVerifiPassShowMessageAndSetStateToMailBoxMenu() {

        when(mailSystem.findMailbox("1")).thenReturn(currentMailbox);
        when(currentMailbox.checkPasscode("2")).thenReturn(true);

        connection.dial("1");
        connection.dial("#");
        connection.dial("2");
        connection.dial("#");
        verify(phone).speak(MAILBOX_MENU_TEXT);
        assert (connection.isInMailBoxMenu());
    }

    @Test
    public void inLoginShouldVerifiPassReturnFalseAndShowErrorMessage() {
        when(mailSystem.findMailbox("1")).thenReturn(currentMailbox);
        when(currentMailbox.checkPasscode("2")).thenReturn(false);

        connection.dial("1");
        connection.dial("#");
        connection.dial("2");
        connection.dial("#");
        verify(phone).speak("Incorrect passcode. Try again!");
    }
}