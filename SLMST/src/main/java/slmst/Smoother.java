/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slmst;

/**
 *
 * @author JKChang
 */
abstract class Smoother {

    int docCount;
    int docLen;
    int collCount;
    int collLen;

    public double gotDocTF() {
        return docCount / docLen;
    }

    public double gotCollTF() {
        return collCount / collLen;
    }

    abstract double smooth();

}
