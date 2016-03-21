import static org.junit.Assert.*;

import org.junit.Test;


public class _MessageQueueTest {

	@Test
	public void newQueueShouldHaveSizeZero() {
		MessageQueue queue = new MessageQueue();
		assertEquals(0, queue.size());
	}
	
	@Test
	public void queueWithOneMessageShouldHaveSizeOne() {
		MessageQueue queue = new MessageQueue();
		queue.add(new Message("Hola"));
		assertEquals(1, queue.size());
	}

}
