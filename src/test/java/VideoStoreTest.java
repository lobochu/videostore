import org.junit.Assert;
import org.junit.Test;

public class VideoStoreTest {

  private final Customer customer = new Customer("Fred", new PricePlan(new LoyaltyPlan(), new Tariff()));

  @Test
  public void testSingleNewReleaseStatement() {
    customerRentsMovieForPeriod(newRelease("The Cell"), 3);
    Assert.assertEquals("Rental Record for Fred\n\tThe Cell\t9.0\nYou owed 9.0\nYou earned 2 frequent renter points\n",
        generate());
  }

  private String generate() {
    return customer.generateStatement(new StatementGenerator());
  }

  @Test
  public void testDualNewReleaseStatement() {
    customerRentsMovieForPeriod(newRelease("The Cell"), 3);
    customerRentsMovieForPeriod(newRelease("The Tigger Movie"), 3);
    Assert.assertEquals("Rental Record for Fred\n\tThe Cell\t9.0\n\tThe Tigger Movie\t9.0\nYou owed 18.0\nYou earned 4 frequent renter points\n",
        generate());
  }

  @Test
  public void testSingleChildrensStatement() {
    customerRentsMovieForPeriod(children("The Tigger Movie"), 3);
    Assert.assertEquals("Rental Record for Fred\n\tThe Tigger Movie\t1.5\nYou owed 1.5\nYou earned 1 frequent renter points\n",
        generate());
  }

  @Test
  public void testMultipleRegularStatement() {
    customerRentsMovieForPeriod(regularMovie("Plan 9 from Outer Space"), 1);
    customerRentsMovieForPeriod(regularMovie("8 1/2"), 2);
    customerRentsMovieForPeriod(regularMovie("Eraserhead"), 3);

    Assert.assertEquals("Rental Record for Fred\n\tPlan 9 from Outer Space\t2.0\n\t8 1/2\t2.0\n\tEraserhead\t3.5\nYou owed 7.5\nYou earned 3 frequent renter points\n",
        generate());
  }

  private void customerRentsMovieForPeriod(Movie movie, int daysRented) {
    customer.addRental(new Rental(movie, daysRented));
  }

  private Movie newRelease(String title) {
    return new Movie(title, Movie.NEW_RELEASE);
  }

  private Movie children(String children) {
    return new Movie(children, Movie.CHILDRENS);
  }

  private Movie regularMovie(String regular) {
    return new Movie(regular, Movie.REGULAR);
  }

}