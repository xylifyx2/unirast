/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format.pwg;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import xylifyx.format.DebugImageStream;
import xylifyx.format.ImageStream;
import xylifyx.format.Raster;
import xylifyx.format.TeeImageStream;
import xylifyx.format.unirast.UrfWriter;

/**
 *
 * @author emartino
 */
public class PwgTest {

    public static void main(String[] args) throws Exception {
        final URL testImageFile = PwgTest.class.getResource("test.pwg");

        InputStream in = testImageFile.openStream();
        DataInputStream din = new DataInputStream(new BufferedInputStream(in));

        final File urfFile = new File(new File(System.getProperty("user.home", ".")), "test.urf");
        OutputStream out = new FileOutputStream(urfFile);
        DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(out));

        final DebugImageStream loggingImageStream = new DebugImageStream() {
            @Override
            public void rasterLine(Raster raster) {
            }
        };
        ImageStream imageWriter = new UrfWriter(dout);

        TeeImageStream tee = new TeeImageStream(loggingImageStream, imageWriter);

        PwgFileReader.loadFromStream(testImageFile.toExternalForm(), din, tee);
    }
}
