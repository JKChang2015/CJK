/* -----------------------------------------------------------------------------

--------------------------------------------------------------------------------
@author JKChang
@data 2016-7-3
 */

package LMST;

public class DirichletSmoother extends Smoother{

    
    public double smooth(int docCount, int docLen, int collCount, int collLen){
        
        return docCount/docLen;
    }
}

