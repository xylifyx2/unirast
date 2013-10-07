/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author emartino
 */
public class CupsPageHeader {

    private static class uint {
    }

    private static class cups_adv_t {
    }

    private static class cups_bool_t {
    }

    private static class cups_cut_t {
    }

    private static class media_string {
        // char[64]
    }

    private static class cups_order_t {
    }

    private static class cups_cspace_t {
    }

    private static class cups_jog_t {
    }

    private static class uint2 {

        uint x, y;
    }

    private static class float2 {

        float x, y;
    }

    private static class float16 {

        float[] value;
    }

    private static class uint16 {

        uint[] value;
    }

    private static class media_string16 {

        media_string[] value;
    }

    private static class uint_bbox {

        uint left, bottom, right, top;
    }

    private static class float_bbox {

        uint left, bottom, right, top;
    }

    private static class cups_edge_t {
    }

    private static class cups_orient_t {
    }
    /**
     * ** New in CUPS 1.2 ***
     */
    /**
     * ** Standard Page Device Dictionary String Values ***
     */
    media_string MediaClass;		/* MediaClass string */

    media_string MediaColor;		/* MediaColor string */

    media_string MediaType;		/* MediaType string */

    media_string OutputType;		/* OutputType string */

    /**
     * ** Standard Page Device Dictionary Integer Values ***
     */
    uint AdvanceDistance;	/* AdvanceDistance value in points */

    cups_adv_t AdvanceMedia;		/* AdvanceMedia value (@link cups_adv_t@) */

    cups_bool_t Collate;		/* Collated copies value */

    cups_cut_t CutMedia;		/* CutMedia value (@link cups_cut_t@) */

    cups_bool_t Duplex;			/* Duplexed (double-sided) value */

    uint2 HWResolution;	/* Resolution in dots-per-inch */

    uint_bbox ImagingBoundingBox;	/* Pixel region that is painted (points, left, bottom, right, top) */

    cups_bool_t InsertSheet;		/* InsertSheet value */

    cups_jog_t Jog;			/* Jog value (@link cups_jog_t@) */

    cups_edge_t LeadingEdge;		/* LeadingEdge value (@link cups_edge_t@) */

    uint2 Margins;		/* Lower-lefthand margins in points */

    cups_bool_t ManualFeed;		/* ManualFeed value */

    uint MediaPosition;		/* MediaPosition value */

    uint MediaWeight;		/* MediaWeight value in grams/m^2 */

    cups_bool_t MirrorPrint;		/* MirrorPrint value */

    cups_bool_t NegativePrint;		/* NegativePrint value */

    uint NumCopies;		/* Number of copies to produce */

    cups_orient_t Orientation;		/* Orientation value (@link cups_orient_t@) */

    cups_bool_t OutputFaceUp;		/* OutputFaceUp value */

    uint2 PageSize;		/* Width and length of page in points */

    cups_bool_t Separations;		/* Separations value */

    cups_bool_t TraySwitch;		/* TraySwitch value */

    cups_bool_t Tumble;			/* Tumble value */

    /**
     * ** CUPS Page Device Dictionary Values ***
     */
    uint cupsWidth;		/* Width of page image in pixels */

    uint cupsHeight;		/* Height of page image in pixels */

    uint cupsMediaType;		/* Media type code */

    uint cupsBitsPerColor;	/* Number of bits for each color */

    uint cupsBitsPerPixel;	/* Number of bits for each pixel */

    uint cupsBytesPerLine;	/* Number of bytes per line */

    cups_order_t cupsColorOrder;		/* Order of colors */

    cups_cspace_t cupsColorSpace;		/* True colorspace */

    uint cupsCompression;	/* Device compression to use */

    uint cupsRowCount;		/* Rows per band */

    uint cupsRowFeed;		/* Feed between bands */

    uint cupsRowStep;		/* Spacing between lines */

    /**
     * ** Version 2 Dictionary Values ***
     */
    uint cupsNumColors;		/* Number of color compoents @since CUPS 1.2/Mac OS X 10.5@ */

    float cupsBorderlessScalingFactor;
    /* Scaling that was applied to page data @since CUPS 1.2/Mac OS X 10.5@ */
    float2 cupsPageSize;	/* Floating point PageSize (scaling *
     * factor not applied) @since CUPS 1.2/Mac OS X 10.5@ */

    float_bbox cupsImagingBBox;	/* Floating point ImagingBoundingBox
     * (scaling factor not applied, left,
     * bottom, right, top) @since CUPS 1.2/Mac OS X 10.5@ */

    uint16 cupsInteger;	/* User-defined integer values @since CUPS 1.2/Mac OS X 10.5@ */

    float16 cupsReal;		/* User-defined floating-point values @since CUPS 1.2/Mac OS X 10.5@ */

    media_string16 cupsString;	/* User-defined string values @since CUPS 1.2/Mac OS X 10.5@ */

    media_string cupsMarkerType;	/* Ink/toner type @since CUPS 1.2/Mac OS X 10.5@ */

    media_string cupsRenderingIntent;/* Color rendering intent @since CUPS 1.2/Mac OS X 10.5@ */

    media_string cupsPageSizeName;	/* PageSize name @since CUPS 1.2/Mac OS X 10.5@ */

}