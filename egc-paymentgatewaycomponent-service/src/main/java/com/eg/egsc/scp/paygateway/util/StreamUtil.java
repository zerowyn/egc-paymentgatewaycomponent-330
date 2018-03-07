/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.paygateway.util;

import java.io.*;

/**
 * @author gucunyang
 * @since 2018-02-25
 */
public class StreamUtil {

    private StreamUtil() {
    }

    public static void io(Reader in, Writer out) throws IOException {
        io(in, out, -1);
    }

    public static void io(Reader in, Writer out, int bufferSize) throws IOException {
        if (bufferSize == -1) {
            bufferSize = 4096;
        }

        char[] buffer = new char[bufferSize];

        int amount;
        while ((amount = in.read(buffer)) >= 0) {
            out.write(buffer, 0, amount);
        }
    }
}
