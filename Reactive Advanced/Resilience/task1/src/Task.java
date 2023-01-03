import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;

public class Task {

	public static Publisher<String> fallbackIfEmpty(Flux<String> publisher, String fallback) {
		return publisher.defaultIfEmpty(fallback);
	}
}