import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class ReactivePaymentsHistoryJpaRepositoryAdapter
        implements PaymentsHistoryReactiveJpaRepository {

    final PaymentsHistoryJpaRepository repository;
    Scheduler blockingTasksScheduler =
            Schedulers.newBoundedElastic(ConnectionsPool.instance()
                            .size(),
                    Integer.MAX_VALUE,
                    "DataBaseBlockingTasksScheduler");

    public ReactivePaymentsHistoryJpaRepositoryAdapter(PaymentsHistoryJpaRepository repository) {
        this.repository = repository;
    }

    public Flux<Payment> findAllByUserId(String userId) {
       return Flux.fromIterable(repository.findAllByUserId(userId)).subscribeOn(blockingTasksScheduler);
    }
}
