//Question 1
import java.util.ArrayList;
import java.util.List;

// CarType enum
enum CarType {
    SUV,
    SED,
    LUX,
    HATCH,
    // Add more types if needed
}

// Car class
class Car {
    private String model;
    private CarType type;
    private int manufacturingYear;
    private double price;

    public Car(String model, CarType type, int manufacturingYear, double price) {
        this.model = model;
        this.type = type;
        this.manufacturingYear = manufacturingYear;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public CarType getType() {
        return type;
    }

    public int getManufacturingYear() {
        return manufacturingYear;
    }

    public double getPrice() {
        return price;
    }
}
//Question 2
// InsurancePolicy abstract class
abstract class InsurancePolicy {
    private String policyHolderName;
    private int id;
    private Car car;
    private int numberOfClaims;

    public InsurancePolicy(String policyHolderName, int id, Car car, int numberOfClaims) {
        this.policyHolderName = policyHolderName;
        this.id = id;
        this.car = car;
        this.numberOfClaims = numberOfClaims;
    }

    public String getPolicyHolderName() {
        return policyHolderName;
    }

    public int getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public int getNumberOfClaims() {
        return numberOfClaims;
    }

    public abstract double calcPayment(double flatRate);

    public void print() {
        System.out.println("Policy Holder Name: " + policyHolderName);
        System.out.println("Policy ID: " + id);
        System.out.println("Car: " + car.getModel());
        System.out.println("Number of Claims: " + numberOfClaims);
    }

    @Override
    public String toString() {
        return "Policy Holder Name: " + policyHolderName +
                "\nPolicy ID: " + id +
                "\nCar: " + car.getModel() +
                "\nNumber of Claims: " + numberOfClaims;
    }
}
//Question 4
// ThirdPartyPolicy class
class ThirdPartyPolicy extends InsurancePolicy {
    private String comments;

    public ThirdPartyPolicy(String policyHolderName, int id, Car car, int numberOfClaims, String comments) {
        super(policyHolderName, id, car, numberOfClaims);
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    @Override
    public double calcPayment(double flatRate) {
        double carPrice = getCar().getPrice();
        int numberOfClaims = getNumberOfClaims();
        return carPrice / 100 + numberOfClaims * 200 + flatRate;
    }
//Question 5 & 6
    @Override
    public void print() {
        super.print();
        System.out.println("Comments: " + comments);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nComments: " + comments;
    }
}
//Question  6
// ComprehensivePolicy class
class ComprehensivePolicy extends InsurancePolicy {
    private int driverAge;
    private int level;

    public ComprehensivePolicy(String policyHolderName, int id, Car car, int numberOfClaims, int driverAge, int level) {
        super(policyHolderName, id, car, numberOfClaims);
        this.driverAge = driverAge;
        this.level = level;
    }

    public int getDriverAge() {
        return driverAge;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public double calcPayment(double flatRate) {
        double carPrice = getCar().getPrice();
        int numberOfClaims = getNumberOfClaims();
        double premium = carPrice / 50 + numberOfClaims * 200 + flatRate;

        if (driverAge < 30) {
            premium += (30 - driverAge) * 50;
        }

        return premium;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Driver Age: " + driverAge);
        System.out.println("Level: " + level);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nDriver Age: " + driverAge +
                "\nLevel: " + level;
    }
}
//Question 7
// Test code
public class Main {
    public static void main(String[] args) {
        double flatRate = 500.0; // Set the flatRate value

        // Create a mixture of ThirdPartyPolicy and ComprehensivePolicy objects
        List<InsurancePolicy> policies = new ArrayList<>();
        Car car1 = new Car("Model1", CarType.SUV, 2020, 25000.0);
        ThirdPartyPolicy thirdPartyPolicy = new ThirdPartyPolicy("John Doe", 1, car1, 2, "Good driver");
        policies.add(thirdPartyPolicy);

        Car car2 = new Car("Model2", CarType.LUX, 2018, 40000.0);
        ComprehensivePolicy comprehensivePolicy = new ComprehensivePolicy("Jane Smith", 2, car2, 1, 32, 3);
        policies.add(comprehensivePolicy);

        // Print all the policies in the list using the print method
        for (InsurancePolicy policy : policies) {
            policy.print();
            System.out.println();
        }

        // Print all the policies in the list using the toString method
        for (InsurancePolicy policy : policies) {
            System.out.println(policy.toString());
            System.out.println();
        }

        // Calculate the total premium payments
        double totalPremium = 0.0;
        for (InsurancePolicy policy : policies) {
            totalPremium += policy.calcPayment(flatRate);
        }

        // Print the total premium
        System.out.println("Total Premium: $" + totalPremium);
    }
}
