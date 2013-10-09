/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format.unirast;

import java.io.DataOutputStream;
import java.io.IOException;
import xylifyx.format.DataWriter;
import xylifyx.format.Raster;

/**
 * <h4>UNIRAST PackBits Algorithm:</h4>
 * This is sort of an inverted version of the TIFF PackBits algorithm. Code is a
 * signed byte, with three interpretations:
 *
 */
public class UrfRasterLine implements DataWriter<Raster, DataOutputStream> {

    public final static int PIXEL_SIZE = 3;

    public void writeImage(Raster input, DataOutputStream output) throws IOException {
        byte[] line = input.getRasterData();
        // find contiguous similar pixels
        
        int lineRepeat = 0;
        output.writeByte(lineRepeat);
        int width = input.getWidth();
        for (int x = 0; x < width;) {
            int n = Math.min(64, width - x);
            int code = 1 - n;
            output.writeByte(code);

            for (int i = 0; i < n; i++) {
                int index = PIXEL_SIZE * (x + i);
                output.write(line, index, PIXEL_SIZE);
            }

            x += n;
        }
    }

    public void flush(DataOutputStream output) throws IOException {
        output.flush();
    }
}
