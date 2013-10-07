/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format.pwg;

/**
 * <h4>pwg-raster-types-supported (1setOf type2 keyword)	[PWG5102.RAS]</h4>
 * <ul>
 * <li>adobe-rgb-16 [PWG5102.RAS]</li>
 * <li>adobe-rgb-8 [PWG5102.RAS]</li>
 * <li>black-1	[PWG5102.RAS]</li>
 * <li>black-16	[PWG5102.RAS]</li>
 * <li>black-8	[PWG5102.RAS]</li>
 * <li>cmyk-16	[PWG5102.RAS]</li>
 * <li>cmyk-8	[PWG5102.RAS]</li>
 * <li>device-1-16	[PWG5102.RAS]</li>
 * <li>device-1-8	[PWG5102.RAS]</li>
 * <li>device-2-16	[PWG5102.RAS]</li>
 * <li>device-2-8	[PWG5102.RAS]</li>
 * </ul>
 */
public enum RasterFormat {

    black_1(1, 1, ColorSpace.black),
    sgray_1(1, 1, ColorSpace.sGray),
    black_8(8, 8, ColorSpace.black),
    cmyk_8(8, 32, ColorSpace.CMYK),
    sgray_8(8, 8, ColorSpace.sGray),
    srgb_8(8, 24, ColorSpace.sRGB),
    adobe_rgb_8(8, 24, ColorSpace.AdobeRGB),
    black_16(16, 16, ColorSpace.black),
    cmyk_16(16, 64, ColorSpace.CMYK),
    srgb_16(16, 48, ColorSpace.sRGB),
    adobe_rgb_16(16, 48, ColorSpace.AdobeRGB);
    private final int bitsPerColor;
    private final int bitsPerPixel;
    private final ColorSpace colorSpace;

    private RasterFormat(int bitsPerColor, int bitsPerPixel, ColorSpace colorSpace) {
        this.bitsPerColor = bitsPerColor;
        this.bitsPerPixel = bitsPerPixel;
        this.colorSpace = colorSpace;
    }

    public boolean matches(int cupsBitsPerColor, int cupsBitsPerPixel, ColorOrder cupsColorOrder, ColorSpace cupsColorSpace) {
        return bitsPerColor == cupsBitsPerColor && bitsPerPixel == cupsBitsPerPixel
                && colorSpace == cupsColorSpace;
    }
}
