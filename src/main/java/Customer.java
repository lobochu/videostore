import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Customer {
  private final List<Rental> rentals = new ArrayList<>();
  private final Tariff tariff = new Tariff();
  private final LoyaltyPlan loyaltyPlan = new LoyaltyPlan();
  private final String name;

  public Customer(String name) {
    this.name = name;
  }

  public void addRental(Rental rental) {
    rentals.add(rental);
  }

  public String generateStatement() {
    final StatementGenerator statementGenerator = new StatementGenerator();
    return statementGenerator.generate(name, rentals, new PricePlan());
  }


  public class PriceRental {
    public final double price;
    public final int points;
    public final String title;

    public PriceRental(double price, int points, String title) {
      this.price = price;
      this.points = points;
      this.title = title;
    }
  }

  private class PricePlan implements Function<Rental, PriceRental> {
    @Override
    public PriceRental apply(Rental r) {
      return new PriceRental(
          tariff.calculatePrice(r),
          loyaltyPlan.calculatePoints(r),
          r.getMovie().getTitle()
      );
    }
  }

  public static class StatementGenerator {


    public String generate(String name, List<Rental> rentals, PricePlan plan) {
      double totalAmount = 0;
      int frequentRenterPoints = 0;
      String result = "Rental Record for " + name + "\n";

      final List<PriceRental> priced = rentals.stream().map(plan).collect(Collectors.toList());

      for (PriceRental priceRental : priced) {

        double rentalPrice = priceRental.price;

        frequentRenterPoints += priceRental.points;

        result += "\t" + priceRental.title + "\t"
            + String.valueOf(rentalPrice) + "\n";
        totalAmount += rentalPrice;

      }

      result += "You owed " + String.valueOf(totalAmount) + "\n";
      result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points\n";


      return result;
    }
  }
}