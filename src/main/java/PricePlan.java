import java.util.function.Function;

/**
 * Created by twlcbo on 2017/2/2.
 */
public class PricePlan implements Function<Rental, PriceRental> {
        private final Tariff tariff;
        private final LoyaltyPlan loyaltyPlan;

        public PricePlan(LoyaltyPlan loyaltyPlan, Tariff tariff) {
                this.tariff = tariff;
                this.loyaltyPlan = loyaltyPlan;
        }

        @Override public PriceRental apply(Rental r) {
                return new PriceRental(tariff.calculatePrice(r),
                    loyaltyPlan.calculatePoints(r),
                    r.getMovie().getTitle());
        }
}
