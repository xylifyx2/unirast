/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format.unirast;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author emartino
 */
public class UrfPageHeader {
    byte bpp;
    byte colorspace;
    byte duplex;
    byte quality;
    int unknown0;
    int unknown1;
    int width;
    int height;
    int dot_per_inch;
    int unknown2;
    int unknown3;

    public void save(DataOutputStream o) throws IOException {
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
    
}
