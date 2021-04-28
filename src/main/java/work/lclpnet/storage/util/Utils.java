/*
 * Copyright (c) 2021 LCLP.
 *
 * Licensed under the MIT License. For more information, consider the LICENSE file in the project's root directory.
 */

package work.lclpnet.storage.util;

import java.io.File;
import java.io.IOException;

/**
 * A set of utilities.
 */
public class Utils {

    /**
     * @return Whether the platform of the current computer is Windows.
     */
    public static boolean isWindows() {
        return System.getProperty("os.name").contains("Windows");
    }

    /**
     * Makes a file hidden on Windows.
     *
     * @param f The file to hide.
     * @return True, if the file was successfully hidden.
     * @throws IOException If some kind of I/O error occurred.
     */
    public static boolean makeFileHiddenWindows(File f) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "attrib", "+h", f.getAbsolutePath());
        pb.inheritIO();
        try {
            Process p = pb.start();
            if (p.waitFor() == 0) return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

}
