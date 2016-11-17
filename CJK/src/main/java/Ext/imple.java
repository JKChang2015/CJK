package Ext;

import eNanoMapper.Curation.OntoEntity;
import eNanoMapper.Curation.Curator;

/**
 *
 * @author JKChang
 * @date 17-Nov-2016
 * @Description:
 *
 */
public class imple {

    public static void main(String[] args) {
        OntoEntity cl = new OntoEntity();
        cl.setName("algorithm type");
        cl.setLabel("algorithm type LABEL");
        cl.setUri("http://purl.enanomapper.org/onto/ENM_8000001");
        cl.setDef("A chemical entity information format specification is an information content entity which specifies a format in which information about a molecular entity should be encoded. ");
        cl.setSupURI("http://purl.obolibrary.org/obo/IAO_0000030");
        cl.setSupClassName("information content entity");
        
        Curator curator = new Curator();
        System.out.println(curator.curate(cl));
        
    }

}
