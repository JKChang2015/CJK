

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
    public Set<String> termURLs = new HashSet<String>();
    public Set<String> ontoList = new HashSet<String>();
    public String termName;

    public Set<String> getTermURLs() {
        return termURLs;
    }

    public void setTermURLs(Set<String> termURLs) {
        this.termURLs = termURLs;
    }

    public Set<String> getOntoList() {
        return ontoList;
    }

    public void setOntoList(Set<String> ontoList) {
        this.ontoList = ontoList;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }
    
    
    
    

}
