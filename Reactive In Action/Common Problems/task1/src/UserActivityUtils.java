import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserActivityUtils {

	public static Mono<Product> findMostExpansivePurchase(Flux<Order> ordersHistory,
			ProductsCatalog productsCatalog) {

		return ordersHistory.flatMapIterable(Order::getProductsIds).map(productsCatalog::findById).
				reduce((pr1,pr2)  -> {
					if (pr1.getPrice() > pr2.getPrice()){
						return pr1;
 					} else {
						return pr2;
					}
				});
	}
}
