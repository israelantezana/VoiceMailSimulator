import static org.junit.Assert.*;

import org.junit.Test;


public class _MailBoxTest {

	@Test
	public void itShouldReturnTheCorrectPassCode() {
		Mailbox mailBox = new Mailbox("1", "Hello");
		
		assertEquals(true, mailBox.checkPasscode("1"));
		
	}
	
	@Test
	public void agregarUnMensajeYObtenerElMensajeActual() {
		Mailbox mailBox = new Mailbox("1", null);
		Message message = new Message("Hola mundo");
		
		mailBox.addMessage(message);
		
		assertEquals(message, mailBox.getCurrentMessage());
		
	}
	
	@Test
	public void agregarUnMensajeYGuardarElMensajeActual() {
		Mailbox mailBox = new Mailbox("1", null);
		Message message = new Message("Hola mundo");
		
		mailBox.addMessage(message);
		mailBox.saveCurrentMessage();
		
		assertEquals(message, mailBox.getCurrentMessage());
		
	}
	
	@Test
	public void obtenerUnMensajeDeUnaListaVacia() {
		Mailbox mailBox = new Mailbox("1", null);
		
		assertNull(mailBox.getCurrentMessage());
	}
	
	@Test
	public void removerUnMensajeGuardado() {
		Mailbox mailBox = new Mailbox("1", null);
		Message message = new Message("Hola mundo");
		
		mailBox.addMessage(message);
		mailBox.saveCurrentMessage();
		//mailBox.removeCurrentMessage();
		
		assertEquals(message, mailBox.removeCurrentMessage());
		
	}
	
	@Test
	public void removerUnMensajeDeUnaListaVacia() {
		Mailbox mailBox = new Mailbox("1", null);
		
		assertNull(mailBox.removeCurrentMessage());
	}
	
	@Test
	public void guardarMensaje() {
		Mailbox mailBox = new Mailbox("1", null);
		mailBox.saveCurrentMessage();
		
		assertNull(mailBox.removeCurrentMessage());
	}

}
