/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xylifyx.format;

/**
 *
 * @author emartino
 */
public class InvalidFileFormat extends RuntimeException {

    public InvalidFileFormat() {
    }

    public InvalidFileFormat(Throwable cause) {
        super(cause);
    }

    public InvalidFileFormat(String message) {
        super(message);
    }
}
