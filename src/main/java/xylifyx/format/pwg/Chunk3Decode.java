/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format.pwg;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import xylifyx.format.DataReader;
import xylifyx.format.InvalidFileFormat;

/**
 *
 * @author emartino
 */
public class Chunk3Decode implements DataReader<DataInputStream, RasterDataOutput> {

    public static final int CHUNK_SIZE = 3;
    final int pixelHeight, pixelWidth;
    final int bytesPerLine;

    public Chunk3Decode(int pixelWidth, int pixelHeight, int bytesPerLine) {
        this.pixelWidth = pixelWidth;
        this.pixelHeight = pixelHeight;
        this.bytesPerLine = bytesPerLine;
    }

    /**
     * whole color values for that line are run-length encoded using a
     * PackBits-like run-length encoding algorithm: 1 to 128 repeated colors are
     * encoded using an initial octet containing "count - 1" followed by the
     * color value octet(s) while 2 to 128 non-repeating colors are encoded
     * using an initial octet containing "257 - count" followed by the color
     * value octet(s).BytesPerLine = TRUNCATE((BitsPerPixel * Width + 7) / 8)
     *
     * @param in
     * @param out
     * @throws IOException
     */
    @Override
    public void load(DataInputStream in, RasterDataOutput out) throws IOException {
        byte[] chunk = new byte[CHUNK_SIZE];
        byte[] rasterLine = new byte[bytesPerLine];
        for (int y = 0; y < pixelHeight;) {
            int lineRepetition = 1 + (0xFF & (int) in.readByte()); // Unsigned Byte?
            for (int x = 0; x < pixelWidth;) {
                byte chunkRepetition = in.readByte();
                if (chunkRepetition >= 0) {
                    int count = chunkRepetition + 1;
                    //System.out.print("<" + count + ">");
                    in.readFully(chunk);
                    for (int c = 0; c < count; c++) {
                        System.arraycopy(chunk, 0, rasterLine, x * CHUNK_SIZE, chunk.length);
                        x++;
                    }
                } else {
                    int count = -chunkRepetition + 1;
                    in.readFully(rasterLine, x * CHUNK_SIZE, count * CHUNK_SIZE);
                    x += count;
                }
            }
            for (int c = 0; c < lineRepetition; c++) {
                out.rasterLine(y, rasterLine);
                y++;
            }
            Arrays.fill(rasterLine, (byte) 0);
        }
    }
    static byte[] testData = new byte[]{
        (byte) 0x00,
        (byte) 0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
        (byte) 0x02, (byte) 0xFF, (byte) 0xFF, (byte) 0x00, (byte) 0x03,
        (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0x00, (byte) 0xFE,
        (byte) 0xFF, (byte) 0xFF, (byte) 0x00, (byte) 0x00, (byte) 0x00,
        (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0x00, (byte) 0x02,
        (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0x00,
        (byte) 0xFF, (byte) 0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
        (byte) 0x00, (byte) 0x01, (byte) 0xFF, (byte) 0xFF, (byte) 0x00,
        (byte) 0x02, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0x02,
        (byte) 0x00, (byte) 0xFF, (byte) 0x00, (byte) 0x00, (byte) 0x02,
        (byte) 0xFF, (byte) 0xFF, (byte) 0x00, (byte) 0x02, (byte) 0xFF,
        (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0x00, (byte) 0xFF,
        (byte) 0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0x00,
        (byte) 0x00, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0x02,
        (byte) 0xFF, (byte) 0xFF, (byte) 0x00, (byte) 0x03, (byte) 0xFF,
        (byte) 0xFF, (byte) 0xFF, (byte) 0x00, (byte) 0x07, (byte) 0xFF,
        (byte) 0xFF, (byte) 0xFF, (byte) 0x01, (byte) 0x07, (byte) 0xFF,
        (byte) 0x00, (byte) 0x00
    };

    public static void main(String[] args) throws IOException {
        System.out.println(testData.length);
        int bpp = (int) Math.floor((24 * 8 + 7) / 8);

        Chunk3Decode c3d = new Chunk3Decode(8, 8, bpp);

        c3d.load(new DataInputStream(new ByteArrayInputStream(testData)), new RasterDataOutput() {
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

            public void invalidFileFormat(InvalidFileFormat e) throws InvalidFileFormat {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            public void ioException(IOException e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            public void beginPage(PwgPageHeader page) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            public void endPage(PwgPageHeader page) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            public void beginPwg(String uri) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });


    }

    public static String toHex(byte c) {
        int v = 0x100 + (0xFF & (int) c);
        return Integer.toHexString(v).substring(1);
    }
}