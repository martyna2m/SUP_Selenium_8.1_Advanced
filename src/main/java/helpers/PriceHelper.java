package helpers;

import providers.TestDataProvider;

import java.math.BigDecimal;

public class PriceHelper {

    static TestDataProvider testDataProvider = new TestDataProvider();

    public static BigDecimal getTotalPriceByQuantity(BigDecimal price, int quantity) {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public static BigDecimal convertIntToBigDecimal(int intValue) {
        return BigDecimal.valueOf(intValue).setScale(2);

    }

    public static BigDecimal deleteCurrency(String priceWithCurrency) {
        String currencySymbol = testDataProvider.getTestData("currency");
        String priceWithoutCurrency = priceWithCurrency.replace(currencySymbol, "");

        if (priceWithoutCurrency.contains(",")) {
            String finalPrice = priceWithoutCurrency.replace(",", "");
            return new BigDecimal(finalPrice);
        }
        return new BigDecimal(priceWithoutCurrency);
    }
}

