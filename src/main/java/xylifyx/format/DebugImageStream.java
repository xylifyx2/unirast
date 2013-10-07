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

    public void rasterLine(int y, byte[] rasterLine) {
        System.out.print(y + ": ");
        for (int i = 0; i < 24; i += 3) {
            System.out.print(toHex(rasterLine[i]));
            System.out.print(toHex(rasterLine[i + 1]));
            System.out.print(toHex(rasterLine[i + 2]));

            System.out.print(" ");
        }
        System.out.println();
    }

    public void invalidFileFormat(InvalidFileFormat e) {
    }

    public void ioException(IOException e) {
    }

    public void beginPage(PwgPageHeader page) {
    }

    public void endPage(PwgPageHeader page) {
    }

    public void beginPwg(String uri) {
    }

    public static String toHex(byte c) {
        int v = 0x100 + (0xFF & (int) c);
        return Integer.toHexString(v).substring(1);
    }
}
