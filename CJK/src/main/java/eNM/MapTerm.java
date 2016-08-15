package eNM;

/* ----------------------------------------------------------------------------
    mapping the new terms to the 19 ontologies, one by one
   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 04-Aug-2016
 */
import java.util.Set;
import java.util.HashSet;

public class MapTerm {

    private String label;
    private String URI;
    private String ontoName;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public String getOntoName() {
        return ontoName;
    }

    public void setOntoName(String ontoName) {
        this.ontoName = ontoName;
    }

}
