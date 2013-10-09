/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format;

import java.io.IOException;
import xylifyx.format.InvalidFileFormat;
import xylifyx.format.pwg.PwgPageHeader;
import xylifyx.format.pwg.PwgPageHeader;

/**
 *
 * @author emartino
 */
public interface ImageStream {

    public void rasterLine(Raster raster) throws IOException;

    public void invalidFileFormat(InvalidFileFormat e) throws IOException;

    public void ioException(IOException e) throws IOException;

    public void beginPage(PageHeader page) throws IOException;

    public void endPage(PageHeader page) throws IOException;

    public void beginImageDocument(String uri) throws IOException;

    public void endImageDocument(int pages) throws IOException;
}
