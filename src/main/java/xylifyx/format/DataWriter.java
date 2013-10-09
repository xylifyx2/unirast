/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author emartino
 */
public interface DataWriter<Input, Output> {

    public void writeImage(Input input, Output output) throws IOException;

    public void flush(DataOutputStream output) throws IOException;
}
