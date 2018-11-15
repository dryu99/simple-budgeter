package parser;

import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {

        BufferedReader br = null;

        try {
            URL url = new URL("http://lcboapi.com/products/438457");
            InputStreamReader reader = new InputStreamReader(url.openStream());
            br = new BufferedReader(reader);

            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }

            String[] stringArray = sb.toString().split(",");
            for (String s : stringArray) {
                System.out.println(s);
            }

        } catch (MalformedURLException e) {
            System.out.println("Bad url was given");
        } finally {
            if (br != null) {
                br.close();
            }
        }




    }
}
