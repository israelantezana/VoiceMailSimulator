import static org.junit.Assert.*;
import org.junit.Test;

public class _MailSystemTest {

    @Test
    public void shouldReturnTheFirstMailBox(){
        MailSystem mailSystem = new MailSystem(3);
        Mailbox mailbox = mailSystem.findMailbox("1");
        String greeting = "You have reached mailbox 1. \nPlease leave a message now.";
        assertEquals(greeting,mailbox.getGreeting());
    }

    @Test
    public void shouldReturnTheLastMailbox(){
        MailSystem mailSystem = new MailSystem(3);
        Mailbox mailbox = mailSystem.findMailbox("3");
        String greeting = "You have reached mailbox 3. \nPlease leave a message now.";
        assertEquals(greeting,mailbox.getGreeting());
    }

    @Test
    public void shouldReturnReturnNullForNonExistantMailbox(){
        MailSystem mailSystem = new MailSystem(3);
        Mailbox mailbox = mailSystem.findMailbox("4");
        assertNull(mailbox);
    }
}