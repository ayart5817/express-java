package practice.programming.principles;

/**
 * public class DiscountCalculator {
 * public double calculateDiscount(double price, boolean isLoyalCustomer, boolean isFirstPurchase, boolean hasCoupon) {
 * double discount = 0.0;
 * if (isLoyalCustomer) {
 * if (isFirstPurchase) {
 * discount = price * 0.10;
 * } else {
 * discount = price * 0.05;
 * }
 * } else {
 * if (hasCoupon) {
 * discount = price * 0.07;
 * } else {
 * discount = price * 0.02;
 * }
 * }
 * return price - discount;
 * }
 * }
 */
public class DiscountCalculator {
  static   public double calculateDiscount(double price, boolean isLoyalCustomer, boolean isFirstPurchase, boolean hasCoupon) {
        if (isLoyalCustomer && isFirstPurchase) return price * 0.9;
        if (isLoyalCustomer) return price * 0.95;
        if (hasCoupon) return price * 0.93;
        return price * 0.98;

    }

    static void main(String[] args) {
        System.out.println(calculateDiscount(100,true,true,true));
        System.out.println(calculateDiscount(100,true,false,true));
        System.out.println(calculateDiscount(100,false,true,true));
        System.out.println(calculateDiscount(100,false,false,false));
    }
}