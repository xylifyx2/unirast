/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format.unirast;

import java.io.DataOutputStream;
import java.io.IOException;
import xylifyx.format.DataWriter;
import xylifyx.format.ImageStream;
import xylifyx.format.pwg.PwgFileReader;

/**
 * file header (12 bytes)
 * <table>
 * <tr>
 * <td>554e 4952 4153 5400 <td> = "UNIRAST\0"
 * </tr>
 * <tr>
 * <td>0000 0001 <td>= 1 page
 * </tr>
 */
public class UrfHeader implements DataWriter<Integer, DataOutputStream> {

    public static final String MAGIC = "UNIRAST\u0000";

    public void writeImage(Integer pageCount, DataOutputStream output) throws IOException {
        output.write(MAGIC.getBytes());
        output.writeInt(pageCount);
    }

    public void flush(DataOutputStream output) {
    }
}
