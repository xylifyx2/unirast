/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format.unirast;

import java.io.DataOutputStream;
import java.io.IOException;
import xylifyx.format.ImageStream;
import xylifyx.format.InvalidFileFormat;
import xylifyx.format.PageHeader;
import xylifyx.format.Raster;

/**
 *
 * @author emartino
 */
public class UrfWriter implements ImageStream {

    private final DataOutputStream output;

    public UrfWriter(DataOutputStream output) {
        this.output = output;
    }

    public void beginImageDocument(String uri) throws IOException {
        UrfHeader header = new UrfHeader();
        header.writeImage(1, output);
    }

    public void endImageDocument(int pages) throws IOException {
        output.close();
    }
    UrfRasterLine urfRaster = new UrfRasterLine();

    public void rasterLine(Raster raster) throws IOException {
        urfRaster.writeImage(raster, output);
    }

    public void invalidFileFormat(InvalidFileFormat e) {
        throw e;
    }

    public void ioException(IOException e) throws IOException {
        throw e;
    }

    public void beginPage(PageHeader input) throws IOException {
        UrfPageHeader pageHeader = new UrfPageHeader();
        pageHeader.writeImage(input, output);
    }

    public void endPage(PageHeader page) {
    }
}
