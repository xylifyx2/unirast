/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format.pwg;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import xylifyx.format.DataReader;
import xylifyx.format.InvalidFileFormat;

/**
 *
 * @author emartino
 */
public class PwgFile implements DataReader<DataInputStream, RasterDataOutput> {
// #  define CUPS_RASTER_SYNCv2	0x52615332	/* RaS2 */
// #  define CUPS_RASTER_SYNC_PWG	CUPS_RASTER_SYNCv2

    /**
     * A version 2 raster file begins with a 32-bit synchronization word:
     * 0x52615332 ("RaS2") for big-endian architectures
     */
    public final static String RaS2 = "RaS2";

    public static void loadFromStream(String uri,
            DataInputStream input, RasterDataOutput output) throws IOException {
        PwgFile pwg = new PwgFile(uri);
        pwg.load(input, output);
    }
    private final String uri;

    private PwgFile(String uri) {
        this.uri = uri;
    }

    public void load(DataInputStream input, RasterDataOutput output) {

        try {
            // PWG Magic 
            byte[] header = new byte[RaS2.length()];
            input.read(header);

            if (!RaS2.equals(new String(header))) {
                throw new InvalidFileFormat();
            }
            output.beginPwg(uri);
            // For each page
            for (;;) {
                PwgPageHeader page = new PwgPageHeader();
                try {
                    page.load(input, output);
                } catch (EOFException e) {
                    Debug.endOfFile(e);
                    break;
                }
                output.beginPage(page);
                if (page.pwgRasterFormat == null) {
                    throw new InvalidFileFormat("Invalid PWG rasterFormat: " + page);
                }
                switch (page.pwgRasterFormat) {
                    case srgb_8: {
                        Chunk3Decode decoder = new Chunk3Decode(page.cupsWidth, page.cupsHeight, page.cupsBytesPerLine);
                        decoder.load(input, output);
                    }
                    break;
                    default:
                        throw new InvalidFileFormat("Unsupported rasterFormat: " + page.pwgRasterFormat);
                }
                output.endPage(page);
            }
        } catch (InvalidFileFormat e) {
            output.invalidFileFormat(e);
        } catch (IOException e) {
            output.ioException(e);
        }
    }
}
