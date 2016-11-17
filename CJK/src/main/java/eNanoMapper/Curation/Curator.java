package eNanoMapper.Curation;

/**
 *
 * @author JKChang
 * @date 17-Nov-2016
 * @Description:
 *
 */
public class Curator {

    public String curate(OntoEntity entity) {

        StringBuffer buffer = new StringBuffer();
        buffer.append('\n');
        buffer.append(printSpliter(entity) + "\n");
        buffer.append(printStrt());
        buffer.append(printUri(entity) + '\n');
        if (entity.getSupURI() != "") {
            buffer.append(printSupURI(entity) + '\n');
        }
        if (entity.getDef() != "") {
            buffer.append(printDef(entity) + '\n');
        }
        buffer.append(printLable(entity) + '\n');
        buffer.append(printEnd() + '\n');

        System.out.println("Added --> " + entity.getName());

        return buffer.toString();
    }

//=============================================================================
    public String printSpliter(OntoEntity entity) {
        return "\t" + "<!-- " + entity.getUri() + " -->" + "\n";
    }

    public String printStrt() {
        return "\t" + "<owl:Class ";
    }

    public String printEnd() {
        return "\t" + "</owl:Class>" + "\n\n";
    }

    public String printLable(OntoEntity entity) {

        return "\t\t" + "<rdfs:label xml:lang=\"en\">" + entity.getLabel()
                + "</rdfs:label>";
    }

    public String printUri(OntoEntity entity) {

        return "rdf:about=\"" + entity.getUri() + "\">";
    }

    public String printSupURI(OntoEntity entity) {

        return "\t\t" + "<rdfs:subClassOf rdf:resource=\"" + entity.getSupURI()
                + "\"/>";
    }

    public String printDef(OntoEntity entity) {

        return "\t\t" + "<rdfs:isDefinedBy xml:lang=\"en\">" + entity.getDef()
                + "</rdfs:isDefinedBy>";
    }

}
