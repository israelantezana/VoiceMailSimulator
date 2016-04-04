import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;


public class _ConnectionMailboxSystemMenuTest {

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
    public void inMailSystemMenuItShouldChangePasscode() {
        when(mailSystem.findMailbox("1")).thenReturn(currentMailbox);
        when(currentMailbox.checkPasscode("1")).thenReturn(true);

        connection.dial("1");
        connection.dial("#");
        connection.dial("1");
        connection.dial("#");
        connection.dial("2");
        connection.dial("9");
        connection.dial("#");
        verify(currentMailbox).setPasscode("9");
        assert (connection.isInMailBoxMenu());
        verify(phone,times(2)).speak(MAILBOX_MENU_TEXT);
    }

    @Test
    public void inMailSystemMenuShouldChangeGreeting(){
        when(mailSystem.findMailbox("1")).thenReturn(currentMailbox);
        when(currentMailbox.checkPasscode("1")).thenReturn(true);

        connection.dial("1");
        connection.dial("#");
        connection.dial("1");
        connection.dial("#");
        connection.dial("3");
        connection.record("Greeting");
        connection.dial("#");
        verify(currentMailbox).setGreeting("Greeting");
        assert(connection.isInMailBoxMenu());
        verify(phone,times(2)).speak(MAILBOX_MENU_TEXT);
    }

}
