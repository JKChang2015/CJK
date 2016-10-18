package eNM.ExcelToOWL;

/**
 * Load term list from excel file and add to OWL file. Function: 1. load excel
 * (term name, superclass and definition) 2. load owlfile as txt file 3. convert
 * term list to owl file 4. save the owl file
 *
 * The basic information need to be added to ontology: 1. term name 2. url 3.
 * term label 4. definition 5. superclass
 *
 * If possible record the new term's url to output
 *
 * @author jkchang
 */
public class ExcelToOWL {

    public static void main(String[] args) {

        String owlFile = "src\\main\\resources\\add.owl";
        String termExcelFile = "src\\main\\resources\\new terms.xls";

        String termURI = "http://purl.enanomapper.org/onto/ENM_0";
        int startID = 5;
        String ID = String.format("%06d", startID);
        String uri = termURI + ID;

        String supUri = "http://purl.enanomapper.org/onto/ENM_0000002";
        String def = "banana def";
        String label = "banana";

        OntoEntity oe = new OntoEntity();
        oe.setLabel(label);
        oe.setDef(def);
        oe.setUri(uri);
        oe.setSupURI(supUri);

        System.out.println(oe.printSpliter());
        System.out.println(oe.printStrt() + oe.printUri());
        System.out.println(oe.printSupURI());
        System.out.println(oe.printDef());
        System.out.println(oe.printLable());
        System.out.println(oe.printEnd());
        System.out.println("");

    }

    String generateURI(String uri, int num) {

        return "";
    }

}
