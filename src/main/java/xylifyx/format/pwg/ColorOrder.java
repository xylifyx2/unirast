/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format.pwg;

/**
 * <h4>Pixel Value Coding</h4>
 *
 * The following sections describe the encoding and decoding of the color values
 * in a CUPS Raster file. In general, colors are packed into the minimum number
 * of bytes, with special consideration provided for efficiency of encoding and
 * access. Multi-byte values are stored in the native byte order and
 * automatically swapped as needed when reading them using the CUPS imaging API.
 */
public enum ColorOrder {

    /**
     * The chunked order provides the pixel value packed in a single place.
     * Pixel values with 8 or more bits per color are stored as an array of
     * colors in order, e.g. for CUPS_CSPACE_RGB you will see 8/16-bits of red,
     * then blue, then green, then red, green, blue, etc. Pixel values with less
     * than 8 bits per color are packed together as shown in Table 4. Multi-byte
     * pixel values are stored in the native word order, just as for 16-bit
     * color values.
     *
     * Table 4: Chunked Color Values
     * <table>
     * <thead>
     * <tr><th>Bits</th><th>	1-color	</th><th>3-color</th><th>	4-color</th><th>
     * 6-color</th>
     * </tr>
     * </thead>
     * <tbody>
     * <tr><td>1</td><td>	W/W/W/W/W/W/W/W	</td><td>0RGB/0RGB</td><td>
     * CMYK/CMYK</td><td>	00KCMYcm</td></tr>
     * <tr><td>2</td><td>	WW/WW/WW/WW	</td><td>00RRGGBB	</td><td>CCMMYYKK
     * </td><td>N/A</td></td></tr>
     * <tr><td>4</td><td>	WWWW/WWWW	</td><td>0000RRRRGGGGBBBB
     * (multi-byte)</td><td>	CCCCMMMMYYYYKKKK (multi-byte)</td><td>
     * N/A</td></tr>
     * </tbody>
     * </table>
     */
    CUPS_ORDER_CHUNKED,
    /**
     * The banded order provides each color as a separate line of data. Each
     * color plane for a line is written in sequence, e.g. for the
     * CUPS_CSPACE_CMYK color space you would see all of the cyan pixels for a
     * line followed by the magenta, yellow, and black pixels for that line.
     * This is repeated for all of the lines on the page. Color values are
     * packed starting with the most-significant bit (MSB) first.
     *
     */
    CUPS_ORDER_BANDED,
    /**
     * The planar order provides each color as a separate page of data using a
     * shared page header. Each color plane for a page is written in sequence,
     * e.g. for the CUPS_CSPACE_CMYK color space you would see all of the cyan
     * pixels for a page followed by the magenta, yellow, and black pixels for
     * that page. Color values are packed starting with the most-significant bit
     * (MSB) first. Each line starts on an 8-bit boundary.
     *
     */
    CUPS_ORDER_PLANAR;
}
