package cz.novros.websocket.example.reactive;

import cz.novros.reactive.websocket.ReactiveClientWebSocket;
import cz.novros.websocket.example.entity.data.Entity;
import cz.novros.websocket.example.jetty.Configuration;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RunClient {

	public static void main(String[] args) throws Exception {
		// Create observable for sending data to server socket.
		final Subject<Entity> data = PublishSubject.create();

		// Set up client socket to connect
		final String uri = "ws://" + Configuration.IP_ADDRESS + ":" + Configuration.PORT + Configuration.REACTIVE_ENDPOINT;
		final ReactiveClientWebSocket<Entity> webSocket = new ReactiveClientWebSocket<Entity>(uri, Entity.class);

		// Subscribe to send data to server socket
		data.subscribe(webSocket);
		// Subscribe to print data from server socket.
		webSocket.map(Entity::getNumber).subscribe(new PrintConsumer());

		// Send some data
		data.onNext(new Entity("Client 4", 4));
		data.onNext(new Entity("Client 3", 3));

		// Wait for user to end.
		System.in.read();

		// Cleanup
		webSocket.close();
	}
}
