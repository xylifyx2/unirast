/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format.pwg;

/**
 *
 * @author emartino
 */
public enum ColorSpace {
    //public final static int black = 3;
//    public final static int sGray = 18;
//    public final static int CMYK = 6;
//    public final static int sRGB = 19;
//    public final static int AdobeRGB = 20;

    gray(0, "(device, typically sRGB-based)"),
    RGB(1, "(device, typically sRGB)"),
    RGBA(2, "(device, typically sRGB)"),
    black(3, "black"),
    CMY(4, "CMY"),
    YMC(5, "YMC"),
    CMYK(6, "CMYK"),
    YMCK(7, "YMCK"),
    KCMY(8, "KCMY"),
    KCMYcm(9, "KCMYcm"),
    GMCK(10, "GMCK"),
    GMCS(11, "GMCS"),
    WHITE(12, "WHITE"),
    GOLD(13, "GOLD"),
    SILVER(14, "SILVER"),
    CIE_XYZ(15, "CIE_XYZ"),
    CIE_Lab(16, "CIE_Lab"),
    RGBW(17, "(sRGB)"),
    sGray(18, "(gray using sRGB gamma/white point)"),
    sRGB(19, "sRGB"),
    AdobeRGB(20, "AdobeRGB");

    private final String prettyPrint;

    public ColorSpace fromIndex(int index) {
        return values()[index];
    }

    public int index() {
        return ordinal();
    }

    private ColorSpace(int index, String prettyPrint) {
        this.prettyPrint = prettyPrint;

        if (index != ordinal()) {
            throw new AssertionError();
        }
    }
}
