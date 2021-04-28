/*
 * Copyright (c) 2021 LCLP.
 *
 * Licensed under the MIT License. For more information, consider the LICENSE file in the project's root directory.
 */

package work.lclpnet.storage;

import work.lclpnet.storage.util.Utils;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;

/**
 * Easily access the local computer's LCLPStorage.
 */
public class LocalLCLPStorage {

    /**
     * Gets a directory from a tree structure.
     *
     * <blockquote>For example,
     * <pre>{@code
     *     File subDir = LocalLCLPStorage.getDirectory("foo", "bar", "xyz");
     *     // will result in: '~/.lclpnetwork/foo/bar/xyz/'
     * }</pre></blockquote>
     *
     * @param tree The folder structure from left to right.
     * @return The sub directory with the given tree.
     * @throws IOException When an I/O error occurred.
     */
    @Nonnull
    public static File getDirectory(String... tree) throws IOException {
        return new File(getRootDirectory(), String.join(File.separator, tree));
    }

    /**
     * Get the root directory of the storage.
     * If it does not yet exist, it will be created.
     *
     * @return The storage root directory as a file.
     * @throws IllegalStateException If the storage root directory could not be created.
     * @throws IOException If an I/O error occurred.
     */
    @Nonnull
    public static File getRootDirectory() throws IllegalStateException, IOException {
        File root = getRootDirectoryFile();
        if (!root.exists() && !createHiddenDirectory(root))
            throw new IllegalStateException("Could not create storage root.");

        return root;
    }

    private static File getRootDirectoryFile() {
        return new File(System.getProperty("user.home"), ".lclpnetwork");
    }

    private static boolean createHiddenDirectory(File dir) throws IOException {
        if (!dir.exists()) {
            if (!dir.mkdirs()) return false;
            else if (Utils.isWindows()) return Utils.makeFileHiddenWindows(dir);
            else return true;
        } else return dir.isDirectory();
    }

}
