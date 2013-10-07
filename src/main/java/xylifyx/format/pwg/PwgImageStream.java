/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format.pwg;

import xylifyx.format.pwg.PwgPageHeader;

/**
 *
 * @author emartino
 */
public interface PwgImageStream {

    public void startOfFile(String uri);

    public void endOfFile();

    public void startOfPage(PwgPageHeader page);

    public void pageRaster(PwgPageHeader page);

    public void endOfPage(PwgPageHeader page);
}
