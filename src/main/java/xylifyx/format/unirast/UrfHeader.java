/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format.unirast;

import java.io.DataOutputStream;
import java.io.IOException;
import xylifyx.format.DataWriter;

/**
 *
 * @author emartino
 */
public class UrfHeader implements DataWriter {
    int pageCount;

    public void save(DataOutputStream o) throws IOException {
        o.writeBytes(Urf.MAGIC);
        o.writeInt(pageCount);
    }
    
}
