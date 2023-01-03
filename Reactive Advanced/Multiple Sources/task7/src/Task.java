import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

public class Task {

    public static Publisher<String> combineSeveralSources(Publisher<String> prefixPublisher,
                                                          Publisher<String> wordPublisher,
                                                          Publisher<String> suffixPublisher) {

        return Flux.combineLatest(el -> "" + el[0] + el[1] + el[2], prefixPublisher, wordPublisher, suffixPublisher);
    }
}