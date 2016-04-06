import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
   This program tests the mail system. A single phone
   communicates with the program through System.in/System.out.
*/
public class MailSystemTester
{
   public static void main(String[] args)
   {
      MailSystem system = new MailSystem(MAILBOX_COUNT);
      Scanner console = new Scanner(System.in);
      List<UserInterface> uis = new ArrayList<UserInterface>();
      UserInterface consola = new Telephone(console);
      GUITelephone gui = new GUITelephone();
      gui.setVisible(true);
      uis.add(gui);
      uis.add(consola);
      Connection c = new Connection(system, uis);
      
      
      consola.run(c);
   }

   private static final int MAILBOX_COUNT = 20;
}
