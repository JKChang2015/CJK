package eNanoMapper.Curation;

/**
 *
 * @author JKChang
 * @date 17-Nov-2016
 * @Description: ontology classes
 *
 */
public class OntoEntity {

    private String name;
    private String label;
    private String uri;
    private String supClassName;
    private String supURI;
    private String def;

    public OntoEntity() {

    }

    public OntoEntity(String name, String label, String uri, String supClassName,
            String supURI, String def) {
        this.name = name;
        this.label = label;
        this.uri = uri;
        this.supClassName = supClassName;
        this.supURI = supURI;
        this.def = def;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getSupClassName() {
        return supClassName;
    }

    public void setSupClassName(String supClassName) {
        this.supClassName = supClassName;
    }

    public String getSupURI() {
        return supURI;
    }

    public void setSupURI(String supURI) {
        this.supURI = supURI;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

}
