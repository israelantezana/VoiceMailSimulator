import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import static org.mockito.Mockito.*;


public class ConnectionTestMessageMenu {
    Mailbox currentMailbox;
    MailSystem mailSystem;
    Telephone phone;
    Connection connection;

    private static String MAILBOX_MENU_TEXT = "Enter 1 to listen to your messages\n"
            + "Enter 2 to change your passcode\n"
            + "Enter 3 to change your greeting";
    private static String MESSAGE_MENU_TEXT = "Enter 1 to listen to the current message\n"
            + "Enter 2 to save the current message\n"
            + "Enter 3 to delete the current message\n"
            + "Enter 4 to return to the main menu";

    @Before
    public void setup() {
        currentMailbox = mock(Mailbox.class);
        mailSystem = mock(MailSystem.class);
        phone = mock(Telephone.class);
        connection = new Connection(mailSystem, phone);

        when(mailSystem.findMailbox("1")).thenReturn(currentMailbox);
        when(currentMailbox.checkPasscode("1")).thenReturn(true);
        dialToMailboxMenu();

    }

    @Test
    public void inMessageMenuListenMessageNoMessages(){
        when(currentMailbox.getCurrentMessage()).thenReturn(null);
        connection.dial("1");
        verify(phone).speak("No messages.\n"+MESSAGE_MENU_TEXT);
    }


    @Test
    public void inMessageMenuListenCurrentMessage(){
        Message message = new Message("This is a message.");
        when(currentMailbox.getCurrentMessage()).thenReturn(message);
        connection.dial("1");
        assertEquals("This is a message.",message.getText());
        verify(phone).speak("This is a message.\n"+MESSAGE_MENU_TEXT);
    }

    @Test
    public void inMessageMenuSaveCurrentMessage(){
        connection.dial("2");
        verify(currentMailbox).saveCurrentMessage();
        verify(phone,times(2)).speak(MESSAGE_MENU_TEXT);
    }

    @Test
    public void inMessageMenuRemoveCurrentMessage() {
        connection.dial("3");
        verify(currentMailbox).removeCurrentMessage();
        verify(phone,times(2)).speak(MESSAGE_MENU_TEXT);
    }

    @Test
    public void inMessageMenuReturnToMailboxMenu(){
        connection.dial("4");
        assert (connection.isInMailBoxMenu());
        verify(phone,times(2)).speak(MAILBOX_MENU_TEXT);
    }

    private void dialToMailboxMenu() {
        connection.dial("1");
        connection.dial("#");
        connection.dial("1");
        connection.dial("#");
        connection.dial("1");
    }

}
