package xylifyx.format.pwg;

import xylifyx.format.ImageStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import xylifyx.format.DataReader;
import xylifyx.format.PageHeader;

/**
 * All "image/cups" streams MUST initialize the
 * <ul>
 * <li> cupsBitsPerColor,
 * <li> cupsBitsPerPixel,
 * <li> cupsBytesPerLine,
 * <li> cupsColorOrder,
 * <li> cupsColorSpace,
 * <li> cupsHeight,
 * <li> cupsWidth, and
 * <li> HWResolution fields in the page header.
 * </ul>
 * All other fields MUST be initialized to 0.
 */
public class PwgPageHeader implements PageHeader, DataReader<DataInputStream, ImageStream> {

    public void readInput(DataInputStream input, ImageStream output) throws IOException {
        int pos;

        pos = 0;
        pos += readZeros(276 - pos, input);
        hwResolutionHorizontal = input.readInt();
        hwResolutionVertical = input.readInt();
        pos += 8;

        pos += readZeros(372 - pos, input);
        cupsWidth = input.readInt();
        cupsHeight = input.readInt();
        pos += 8;

        pos += readZeros(384 - pos, input);
        cupsBitsPerColor = input.readInt();
        cupsBitsPerPixel = input.readInt();
        cupsBytesPerLine = input.readInt();
        cupsColorOrder = ColorOrder.values()[input.readInt()];
        cupsColorSpace = ColorSpace.values()[input.readInt()];
        pos += 20;
        pos += readZeros(1796 - pos, input);

        for (PwgRasterFormat rf : PwgRasterFormat.values()) {
            if (rf.matches(cupsBitsPerColor, cupsBitsPerPixel, cupsColorOrder, cupsColorSpace)) {
                pwgRasterFormat = rf;
                break;
            }
        }
        if (pwgRasterFormat == null) {
            Logger.getLogger(PwgPageHeader.class.getName()).log(
                    Level.WARNING, "RasterFormat not valid PWG: cupsBitsPerColor={0}, cupsBitsPerPixel={1}, cupsColorOrder={2}, cupsColorSpace={3}", new Object[]{cupsBitsPerColor, cupsBitsPerPixel, cupsColorOrder, cupsColorSpace});
        }
    }
    public PwgRasterFormat pwgRasterFormat;
    /**
     * 276-283	Unsigned Integers (2)	HWResolution	Horizontal and vertical
     * resolution in dots-per-inch.
     */
    public int hwResolutionHorizontal;
    public int hwResolutionVertical;
    /**
     * 372-375	Unsigned Integer	cupsWidth	Width of page image in pixels
     */
    public int cupsWidth;
    /**
     * 376-379	Unsigned Integer	cupsHeight	Height of page image in pixels
     */
    public int cupsHeight;
    /**
     * 384-387	int	cupsBitsPerColor
     *
     * 1, 2, 4, 8, and 16 bits for version 2/3 raster files
     */
    public int cupsBitsPerColor;
    /**
     * 388-391	Unsigned Integers	cupsBitsPerPixel
     *
     * 1 to 240 bits for version 2/3 raster files
     */
    public int cupsBitsPerPixel;
    /**
     * 392-395	Unsigned Integers cupsBytesPerLine	1 to 2^32 - 1 bytes
     */
    public int cupsBytesPerLine;
    /**
     * 396-399	Unsigned Integers	cupsColorOrder
     *
     * <ul>
     * <li>0 = chunky pixels (CMYK CMYK CMYK)
     * <li>1 = banded pixels (CCC MMM YYY KKK)
     * <li>2 = planar pixels (CCC... MMM... YYY... KKK...)
     * </ul>
     */
    public ColorOrder cupsColorOrder;
    /**
     * 400-403	int	cupsColorSpace
     */
    public ColorSpace cupsColorSpace;

    @Override
    public String toString() {
        return "PwgPageHeader{" + "hwResolutionHorizontal=" + hwResolutionHorizontal + ", hwResolutionVertical=" + hwResolutionVertical + ", cupsWidth=" + cupsWidth + ", cupsHeight=" + cupsHeight + ", cupsBitsPerColor=" + cupsBitsPerColor + ", cupsBitsPerPixel=" + cupsBitsPerPixel + ", cupsBytesPerLine=" + cupsBytesPerLine + ", cupsColorOrder=" + cupsColorOrder + ", cupsColorSpace=" + cupsColorSpace + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.hwResolutionHorizontal;
        hash = 97 * hash + this.hwResolutionVertical;
        hash = 97 * hash + this.cupsWidth;
        hash = 97 * hash + this.cupsHeight;
        hash = 97 * hash + this.cupsBitsPerColor;
        hash = 97 * hash + this.cupsBitsPerPixel;
        hash = 97 * hash + this.cupsBytesPerLine;
        hash = 97 * hash + (this.cupsColorOrder != null ? this.cupsColorOrder.hashCode() : 0);
        hash = 97 * hash + (this.cupsColorSpace != null ? this.cupsColorSpace.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PwgPageHeader other = (PwgPageHeader) obj;
        if (this.hwResolutionHorizontal != other.hwResolutionHorizontal) {
            return false;
        }
        if (this.hwResolutionVertical != other.hwResolutionVertical) {
            return false;
        }
        if (this.cupsWidth != other.cupsWidth) {
            return false;
        }
        if (this.cupsHeight != other.cupsHeight) {
            return false;
        }
        if (this.cupsBitsPerColor != other.cupsBitsPerColor) {
            return false;
        }
        if (this.cupsBitsPerPixel != other.cupsBitsPerPixel) {
            return false;
        }
        if (this.cupsBytesPerLine != other.cupsBytesPerLine) {
            return false;
        }
        if (this.cupsColorOrder != other.cupsColorOrder) {
            return false;
        }
        if (this.cupsColorSpace != other.cupsColorSpace) {
            return false;
        }
        return true;
    }

    private static int readZeros(final int zeroCount, DataInputStream o) throws IllegalArgumentException, IOException {
        for (int i = 0; i < zeroCount; i++) {
            if (o.readByte() != 0) {
                //throw new IllegalArgumentException("not zero");
            }
        }
        return zeroCount;
    }

    public byte getBytesPerPixel() {
        return getBytesPerPixel();
    }

    public int getResolutionX() {
        return hwResolutionHorizontal;
    }

    public int getHeight() {
        return cupsHeight;
    }

    public int getWidth() {
        return cupsWidth;
    }
}
