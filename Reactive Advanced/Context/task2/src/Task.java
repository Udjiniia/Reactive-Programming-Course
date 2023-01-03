import reactor.core.publisher.Mono;
import reactor.util.context.Context;
import reactor.util.context.ContextView;

import java.util.Map;
import java.util.stream.Stream;

public class Task {

    public static Mono<String> provideCorrectContext(Mono<String> source,
                                                     Object key,
                                                     Object value) {
        return source.contextWrite(Context.of(key, value));
    }
}