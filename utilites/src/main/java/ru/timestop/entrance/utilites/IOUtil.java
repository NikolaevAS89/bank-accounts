package ru.timestop.entrance.utilites;

import java.io.*;
import java.net.HttpURLConnection;

/**
 * @author NikolaevAS89
 * @version 1.0.0
 * @since 12.10.2017
 */
public class IOUtil {

    public static void copy(InputStream is, OutputStream out) throws IOException {
        redirectStream(is, out, 256);
    }

    public static void redirectStream(InputStream is, OutputStream os, int bufferSize) throws IOException {
        try {
            byte[] buff = new byte[bufferSize];
            int readed = 0;
            do {
                os.write(buff, 0, readed);
                readed = is.read(buff);
                os.flush();
            } while (readed > 0);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void closeQuiet(Closeable io) {
        try {
            io.close();
        } catch (Exception e) {
            //SKIP
        }
    }


    public static void closeQuiet(HttpURLConnection connection) {
        try {
            if (connection != null) {
                connection.disconnect();
            }
        } catch (Exception e) {
            //SKIP
        }
    }

    public static void flushQuiet(Flushable flushable) {
        try {
            flushable.flush();
        } catch (IOException e) {
            //SKIP
        }
    }
}
