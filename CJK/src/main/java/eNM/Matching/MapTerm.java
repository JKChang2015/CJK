package eNM.Matching;

/* ----------------------------------------------------------------------------
    mapping the new terms to the 19 ontologies, one by one
   ---------------------------------------------------------------------------- 
 @author jkchang
 @date 04-Aug-2016
 */

public class MapTerm {

    private String label;
    private String URI;
    private String ontoName;
    
    public MapTerm(){
        
    }
    
    public MapTerm(String ontoName, String label, String URI){
        this.ontoName = ontoName;
        this.label = label;
        this.URI = URI;
    }

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
