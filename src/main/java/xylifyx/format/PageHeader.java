/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format;

/**
 *
 * @author emartino
 */
public interface PageHeader {

    public byte getBytesPerPixel();

    public int getResolutionX();

    public int getHeight();

    public int getWidth();
    
}
