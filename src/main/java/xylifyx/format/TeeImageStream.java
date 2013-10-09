/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format;

import java.io.IOException;

/**
 *
 * @author emartino
 */
public class TeeImageStream implements ImageStream {

    ImageStream a, b;

    public TeeImageStream(ImageStream a, ImageStream b) {
        this.a = a;
        this.b = b;
    }

    public void rasterLine(Raster raster) throws IOException {
        a.rasterLine(raster);
        b.rasterLine(raster);
    }

    public void invalidFileFormat(InvalidFileFormat e) throws IOException {
        a.invalidFileFormat(e);
        b.invalidFileFormat(e);
    }

    public void ioException(IOException e) throws IOException {
        a.ioException(e);
        b.ioException(e);
    }

    public void beginPage(PageHeader page) throws IOException {
        a.beginPage(page);
        b.beginPage(page);
    }

    public void endPage(PageHeader page) throws IOException {
        a.endPage(page);
        b.endPage(page);
    }

    public void beginImageDocument(String uri) throws IOException {
        a.beginImageDocument(uri);
        b.beginImageDocument(uri);
    }

    public void endImageDocument(int pages) throws IOException {
        a.endImageDocument(pages);
        b.endImageDocument(pages);
    }
}
