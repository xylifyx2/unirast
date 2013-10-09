/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format;

import java.io.IOException;
import xylifyx.format.pwg.PwgPageHeader;

/**
 *
 * @author emartino
 */
public class DebugImageStream implements ImageStream {

    public void rasterLine(Raster raster) {
        System.out.print(raster.getY() + ": ");
        byte[] r = raster.getRasterData();
        for (int i = 0; i < 24; i += 3) {
            System.out.print(toHex(r[i]));
            System.out.print(toHex(r[i + 1]));
            System.out.print(toHex(r[i + 2]));

            System.out.print(" ");
        }
        System.out.println();
    }

    public void invalidFileFormat(InvalidFileFormat e) {
        throw e;
    }

    public void ioException(IOException e) {
        throw new InvalidFileFormat(e);
    }

    public void beginPage(PageHeader page) {
        System.out.println(page);
    }

    public void endPage(PageHeader page) {
        System.out.println("page done");
    }

    public void beginImageDocument(String uri) {
        System.out.println("begin file " + uri);
    }

    public static String toHex(byte c) {
        int v = 0x100 + (0xFF & (int) c);
        return Integer.toHexString(v).substring(1);
    }

    public void endImageDocument(int pages) {
        System.out.println("end file " + pages);
    }
}
