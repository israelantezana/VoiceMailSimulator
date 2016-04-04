import static org.junit.Assert.*;

import org.junit.Test;


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ConnectionTestGeneral {

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
    }

    @Test
    public void resetConnectionShouldShowInitialPromotAndSetStateToConnected() {
        verify(phone).speak("Enter mailbox number followed by #");
        assert (connection.isConnected());
    }

    @Test
    public void asConnectedDial1shouldGetMailBoxSpeakGreetingAndSetStateToRecording() {
        when(mailSystem.findMailbox("1")).thenReturn(currentMailbox);
        connection.dial("1");
        connection.dial("#");
        verify(phone).speak(currentMailbox.getGreeting());
        assert (connection.isRecording());
    }

    @Test
    public void asConnectedDial10shouldGetNullSpeakErrorMsjAndSetStateToRecording() {
        when(mailSystem.findMailbox("10")).thenReturn(null);
        connection.dial("1");
        connection.dial("#");
        verify(phone).speak("Incorrect mailbox number. Try again!");
    }

    @Test
    public void inMailSystemMenuChangePasscode() {
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

    @Test
    public void afterRecordingHangoutShouldSaveAMessageAndResetConnection(){
        String msgText = "This is a new message.";
        when(mailSystem.findMailbox("1")).thenReturn(currentMailbox);
        when(currentMailbox.checkPasscode("1")).thenReturn(true);
        when(currentMailbox.getCurrentMessage()).thenReturn(new Message(msgText));

        connection.dial("1");
        connection.dial("#");
        connection.dial(msgText);
        connection.hangup();
        connection.dial("1");
        connection.dial("#");
        connection.dial("1");
        connection.dial("#");
        connection.dial("1");
        connection.dial("1");
        verify(phone).speak(msgText+"\n"+MESSAGE_MENU_TEXT);
    }
}
