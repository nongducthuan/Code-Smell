import java.util.List;
import java.util.ArrayList;

class Property {
    private final String name;
    private final double rentAmount;
    private final String ownerName;
    private final String location;

    public Property(String name, double rentAmount, String ownerName, String location) {
        this.name = name;
        this.rentAmount = rentAmount;
        this.ownerName = ownerName;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public double getRentAmount() {
        return rentAmount;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getLocation() {
        return location;
    }
}

class PropertyReportItem {
    private final Property property;
    private final double yearlyRent;
    private final String category;

    public PropertyReportItem(Property property, double yearlyRent, String category) {
        this.property = property;
        this.yearlyRent = yearlyRent;
        this.category = category;
    }

    public String generateReportLine() {
        return "Property: " + property.getName() + "\n" +
                "Rent Amount: " + property.getRentAmount() + "\n" +
                "Owner: " + property.getOwnerName() + "\n" +
                "Location: " + property.getLocation() + "\n" +
                "Category: " + category + "\n" +
                "Yearly Rent: " + yearlyRent;
    }
}

class FinancialReportService {
    private final double premiumThreshold;

    public FinancialReportService(double premiumThreshold) {
        this.premiumThreshold = premiumThreshold;
    }

    public List<PropertyReportItem> generateReportItems(List<Property> properties) {
        List<PropertyReportItem> reportItems = new ArrayList<>();
        for (var property : properties) {
            double yearlyRent = property.getRentAmount() * 12.0;
            String category = property.getRentAmount() > premiumThreshold ? "Premium" : "Standard";
            reportItems.add(new PropertyReportItem(property, yearlyRent, category));
        }

        return reportItems;
    }

    public double calculateTotalRent(List<Property> properties) {
        return properties.stream().mapToDouble(Property::getRentAmount).sum();
    }
}

class ReportPrinter {
    public void printFinancialReport(String title, List<PropertyReportItem> reportItems, double totalRent) {
        System.out.println("Financial Report: " + title);
        System.out.println("----------------------------");

        for (var item : reportItems) {
            System.out.println(item.generateReportLine());
            System.out.println("--------------------");
        }

        System.out.printf("Total Monthly Rent: $%.2f%n", totalRent);
        System.out.printf("Total Yearly Rent: $%.2f%n", totalRent * 12);
    }
}

public class ReportGenerator {
    public static void main(String[] args) {
        List<Property> properties = List.of(
                new Property("Apartment A", 1500.0, "John Doe", "City Center"),
                new Property("House B", 2000.0, "Jane Smith", "Suburb"),
                new Property("Condo C", 1800.0, "Bob Johnson", "Downtown"));

        FinancialReportService reportService = new FinancialReportService(2000.0);

        List<PropertyReportItem> reportItems = reportService.generateReportItems(properties);
        double totalRent = reportService.calculateTotalRent(properties);

        ReportPrinter printer = new ReportPrinter();
        printer.printFinancialReport("Monthly Rent Summary", reportItems, totalRent);
    }
}