import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by twlcbo on 2017/2/2.
 */
public class StatementGenerator {

        public String generate(String name, List<Rental> rentals, PricePlan plan) {
                double totalAmount = 0;
                int frequentRenterPoints = 0;
                String result = "Rental Record for " + name + "\n";

                final List<PriceRental> priced = rentals.stream().map(plan).collect(Collectors.toList());

                for (PriceRental priceRental : priced) {

                        double rentalPrice = priceRental.price;

                        frequentRenterPoints += priceRental.points;

                        result += "\t" + priceRental.title + "\t" + String.valueOf(rentalPrice) + "\n";
                        totalAmount += rentalPrice;

                }

                result += "You owed " + String.valueOf(totalAmount) + "\n";
                result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points\n";

                return result;
        }
}
