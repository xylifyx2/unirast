/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format.pwg;

import xylifyx.format.ImageStream;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import xylifyx.format.InvalidFileFormat;

/**
 *
 * @author emartino
 */
public class PwgTest {

    public static void main(String[] args) throws Exception {
        final URL testImageFile = PwgTest.class.getResource("test.pwg");
        InputStream in = testImageFile.openStream();
        DataInputStream din = new DataInputStream(new BufferedInputStream(in));

        PwgFileReader.loadFromStream(testImageFile.toExternalForm(), din, new ImageStream() {

            public void rasterLine(int y, byte[] rasterLine) {
             }

            public void invalidFileFormat(InvalidFileFormat e) {
                throw e;
            }

            public void ioException(IOException e) {
                throw new InvalidFileFormat(e);
            }

            public void beginPage(PwgPageHeader page) {
                System.out.println(page);
            }

            public void endPage(PwgPageHeader page) {
                System.out.println("page done");
            }

            public void beginPwg(String uri) {
                 System.out.println("load file "+uri);
            }
        });

    }
}
