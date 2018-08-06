/**
 * Created by jkchang
 * 31/07/2018
 * Tag:
 * Description:
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testing {

    public static void main(String[] args) {

        String iri = "+D::(www.superclass.com) lalalala";
        String pattern = "([\\-,\\+]*)(['U','S','D']*)(\\:*)(\\(\\S+\\))*(\\:)(\\(\\S+\\))*\\s*\\S*";

        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(iri);
        if (m.find()) {
            System.out.println("full match  -> " + m.group(0));
            System.out.println("save/remove instructor:     " + m.group(1));
            System.out.println("direction instructor:       " + m.group(2));
            System.out.println("target iri:     " + m.group(3));
            System.out.println("superclass iri:     " + m.group(4));
            System.out.println("comments:       " + m.group(5));
            System.out.println("comments:       " + m.group(6));
        } else {
            System.out.println("NO MATCH");
        }
    }
}
