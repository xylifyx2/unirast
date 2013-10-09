/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author emartino
 */
public interface DataReader<I,O> {
    public void readInput(I input, O output) throws IOException;
}
