package eNM.ExcelToOWL;

/**
 *
 * @author jkchang
 */
public class OntoEntity {

    private String name;
    private String label;
    private String uri;
    private String supClassName;
    private String supURI;
    private String def;

    public String printSpliter() {
        return "<!-- " + uri + " -->" + "\n\n";
    }

    public String printStrt() {
        return "<owl:Class " + "\n";
    }

    public String printEnd() {
        return "</owl:Class>" + "\n\n\n\n";
    }

    //------------------- Term Name -----------------------------------------//
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //------------------------Superclass name----------------------------//
    public String getSupClassName() {
        return supClassName;
    }

    public void setSupClassName(String supClassName) {
        this.supClassName = supClassName;
    }

    //---------------------Label ---------------------------------------//
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String printLable() {

        return "<rdfs:label xml:lang=\"en\">" + label + "</rdfs:label>";
    }

    //---------------------- URI -------------------------------------//
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String printUri() {

        return "rdf:about=\"" + uri + ">";
    }

    //--------------------Superclass URI----------------------------------//
    public String getSupURI() {
        return supURI;
    }

    public void setSupURI(String supURI) {
        this.supURI = supURI;
    }

    public String printSupURI() {

        return "<rdfs:subClassOf rdf:resource=\"" + supURI + "\"/>";
    }

    //--------------------Definition--------------------------------------//
    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String printDef() {

        return "<rdfs:isDefinedBy xml:lang=\"en\">" + def + "</rdfs:isDefinedBy>";
    }

}
