import java.time.Duration;
import java.util.function.Function;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

public class Task {

	public static void unitTestAFunction(Function<Flux<String>, Flux<Long>> functionToTest) {
		testSuccessCase(functionToTest);
		testFailureCase(functionToTest);
	}

	static void testSuccessCase(Function<Flux<String>, Flux<Long>> functionToTest) {
		// prodce "1" "2" "100"
		final TestPublisher<String> testPublisher = TestPublisher.create();
		Flux<Long> results = functionToTest.apply(testPublisher.flux());
		StepVerifier.create(results)
				.then(() -> testPublisher.emit("1", "2", "100"))
				.expectNext(1L, 2L, 100L)
				.verifyComplete();
	}

	static void testFailureCase(Function<Flux<String>, Flux<Long>> functionToTest) {
		// produce non number string and check NumberFormatException is produced
		final TestPublisher<String> testPublisher = TestPublisher.create();
		Flux<Long> results = functionToTest.apply(testPublisher.flux());
		StepVerifier.create(results)
				.then(() -> testPublisher.emit("error"))
				.expectError(NumberFormatException.class)
				.verify();
	}
}