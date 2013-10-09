/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format.unirast;

import java.io.DataOutputStream;
import java.io.IOException;
import xylifyx.format.DataWriter;
import xylifyx.format.PageHeader;

/**
 *
 * // page header (32 bytes)
 *
 * <table border="1" style="border-collapse:collapse;">
 * <tr><td>18 </td><td> 24bpp
 * <tr><td>01 </td><td> color space 1 (values 1-6 mean different things)
 * <tr><td>00 </td><td> duplex mode (no)
 * <tr><td>04 </td><td> quality 4
 * <tr><td>0000 0001 </td><td> unknown
 * <tr><td>0000 0000 </td><td> unknown
 * <tr><td>0000 13ec </td><td> 5100 = page width
 * <tr><td>0000 19c8 </td><td> 6600 = page height
 * <tr><td>0000 0258 </td><td> 600 = dots per inch
 * <tr><td>0000 0000 </td><td> unknown
 * <tr><td>0000 0000 </td><td> unknown
 * </table>
 */
public class UrfPageHeader implements DataWriter<PageHeader, DataOutputStream> {

    public void writeImage(PageHeader input, DataOutputStream o) throws IOException {
        byte bpp = 24;
        byte colorspace = 1;
        byte duplex = 0;
        byte quality = 4;
        int unknown0 = 1;
        int unknown1 = 0;
        int width = input.getWidth();
        int height = input.getHeight();
        int dot_per_inch = input.getResolutionX();
        int unknown2 = 0;
        int unknown3 = 0;

        o.writeByte(bpp);
        o.writeByte(colorspace);
        o.writeByte(duplex);
        o.writeByte(quality);
        o.writeInt(unknown0);
        o.writeInt(unknown1);
        o.writeInt(width);
        o.writeInt(height);
        o.writeInt(dot_per_inch);
        o.writeInt(unknown2);
        o.writeInt(unknown3);
    }

    /**
     *
     * @param output
     */
    public void flush(DataOutputStream output) throws IOException {
        output.flush();
    }
}
