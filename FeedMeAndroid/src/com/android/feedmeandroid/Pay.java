package com.android.feedmeandroid;

import java.util.HashMap;
import java.util.Map;

import com.android.feedmeandroid.exception.StripeException;
import com.android.feedmeandroid.model.Charge;

public class Pay {

    public static void makePayment() {
        Stripe.apiKey = "L8px8dWKTJmab3qzAuq7Vh4hwp3sXbK4";
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        chargeMap.put("amount", 100);
        chargeMap.put("currency", "usd");
        Map<String, Object> cardMap = new HashMap<String, Object>();
        cardMap.put("number", "4242424242424242");
        cardMap.put("exp_month", 12);
        cardMap.put("exp_year", 2012);
        chargeMap.put("card", cardMap);
        try {
            Charge charge = Charge.create(chargeMap);
            System.out.println(charge);
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }
}