/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format.pwg;

import java.io.IOException;
import xylifyx.format.InvalidFileFormat;
import xylifyx.format.pwg.PwgPageHeader;

/**
 *
 * @author emartino
 */
public interface RasterDataOutput {

    public void rasterLine(int y, byte[] rasterLine);

    public void invalidFileFormat(InvalidFileFormat e);

    public void ioException(IOException e);

    public void beginPage(PwgPageHeader page);

    public void endPage(PwgPageHeader page);

    public void beginPwg(String uri);
    
}
