package parser;

import model.Transaction;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

public class JSONReturner {

    // EFFECTS: returns parsed expense from the product data in the given URL
    public static Transaction returnParsedExpenseFrom(String url) throws IOException, ParseException {
        Object o = new JSONParser().parse(new InputStreamReader(new URL(url).openStream()));
        JSONObject jo = (JSONObject) o;

        Map result = (Map) jo.get("result");

        String productName = (String) result.get("name");
        long priceInCents = (long) result.get("price_in_cents");
        double priceInDollars = (double) priceInCents / 100;

        return new Transaction(-1 * (double) priceInDollars, productName);
    }

}
