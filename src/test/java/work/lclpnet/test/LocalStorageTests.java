/*
 * Copyright (c) 2021 LCLP.
 *
 * Licensed under the MIT License. For more information, consider the LICENSE file in the project's root directory.
 */

package work.lclpnet.test;

import org.junit.jupiter.api.Test;
import work.lclpnet.storage.LocalLCLPStorage;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class LocalStorageTests {

    @Test
    void getRootDirectory() throws IOException {
        File f = LocalLCLPStorage.getRootDirectory();
        assertTrue(f.isDirectory());
        assertEquals(f.getName(), ".lclpnetwork");
    }

    @Test
    void getSubDirectory() throws IOException {
        File dir = LocalLCLPStorage.getDirectory("__lclp_storage_test", "foo", "bar");
        String expectedEnd = String.format("%s__lclp_storage_test%sfoo%sbar", File.separator, File.separator, File.separator);
        if(!dir.getAbsolutePath().endsWith(expectedEnd))
            fail("Invalid path: " + dir.getAbsolutePath());
    }

}
