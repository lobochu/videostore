import static java.lang.Math.max;

/**
 * Created by twlcbo on 2017/1/19.
 */
public class VipTariff implements Tariff {

        @Override
        public double calculatePrice(Rental rental) {

                // determines the amount for each line
                final int daysRented = rental.getDaysRented();
                final int priceCode = rental.getMovie().getPriceCode();
                switch (priceCode) {
                        case Movie.REGULAR:
                                return 1.0 + daysRented;
                        case Movie.NEW_RELEASE:
                                return 2.0 * daysRented;
                        case Movie.CHILDRENS:
                                return max(0, daysRented - 2) * 1.0;
                        default:
                                throw new IllegalArgumentException(
                                    "Unhandled price code" + priceCode);

                }
        }
}
