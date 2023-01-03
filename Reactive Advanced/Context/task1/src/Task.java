import reactor.core.publisher.Mono;

public class Task {

	public static Mono<String> grabDataFromTheGivenContext(Object key) {
		return Mono.deferContextual((f) -> Mono.just(f.get(key)));
	}
}