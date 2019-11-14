package edu.student.midlandstech.anthonydmolnar.currency;

public class Currency {
    public int id;
    public String currency;
    public double rate;

    public Currency(int newId, String newCurrency, double newRate) {
        id = newId;
        currency = newCurrency;
        rate = newRate;
    }

    public String getCurrency() {return currency;}
    public int getId() {return id;}
    public double getRate() {return rate;}

    public double toDollar(double amount) {return amount*rate;}

    public String toString() {return id + " " + currency + " " + rate;}
}
