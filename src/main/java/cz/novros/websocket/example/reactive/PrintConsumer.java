package cz.novros.websocket.example.reactive;

import io.reactivex.functions.Consumer;

/**
 * Simple consumer to print accepted entity.
 */
public class PrintConsumer implements Consumer<Object> {

	@Override
	public void accept(final Object o) throws Exception {
		System.out.println("Accept: " + o);
	}
}
