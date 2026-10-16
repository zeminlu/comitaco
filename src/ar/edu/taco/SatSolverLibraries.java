/*
 * TACO: Translation of Annotated COde
 * Copyright (c) 2010 Universidad de Buenos Aires
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA,
 * 02110-1301, USA
 */
package ar.edu.taco;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Locale;

import org.apache.log4j.Logger;

/**
 * Keeps the SAT solver libraries that DynAlloy extracts from {@code alloy4.jar} up to date.
 *
 * <p>Before the first analysis, DynAlloy ({@code AlloyJNILibraryPath}) copies the native solver
 * libraries from the jar's folder for this platform ({@code x86-mac} on macOS, {@code
 * <arch>-linux} on Linux) into {@code <java.io.tmpdir>/alloy4tmp40-<user.name>/binary}. Like
 * Alloy's {@code Util.copy}, it never overwrites a file that is already there. A copy left by an
 * older {@code alloy4.jar} therefore stays: until 2026 the macOS MiniSat 2.2 library was x86_64
 * only, and an arm64 JVM that found that old copy failed with an {@code UnsatisfiedLinkError}.
 * The scripts give every run its own temp directory, but runs from an IDE share the system one.
 *
 * <p>{@link #refreshExtractedLibraries()} deletes the extracted files that differ from the ones in
 * the jar, so that DynAlloy extracts them again. Files that match are left alone.
 */
public final class SatSolverLibraries {

    private static final Logger log = Logger.getLogger(SatSolverLibraries.class);

    private static boolean refreshed = false;

    private SatSolverLibraries() {
    }

    /**
     * Deletes the libraries in DynAlloy's extraction directory that differ from the copies in
     * {@code alloy4.jar}; does nothing after the first call in a JVM. Errors are logged, not
     * thrown: at worst DynAlloy loads the old copy, as it did before.
     */
    public static synchronized void refreshExtractedLibraries() {
        if (refreshed) {
            return;
        }
        refreshed = true;
        File binary = new File(extractionDirectory(), "binary");
        File[] files = binary.listFiles();
        if (files == null) {
            return;
        }
        String platform = platformFolder();
        for (File extracted : files) {
            String resource = platform + "/" + extracted.getName();
            try {
                byte[] inJar = read(SatSolverLibraries.class.getClassLoader().getResourceAsStream(resource));
                if (inJar == null) {
                    continue;
                }
                byte[] onDisk = read(new FileInputStream(extracted));
                if (!Arrays.equals(inJar, onDisk)) {
                    if (extracted.delete()) {
                        log.warn("Deleted " + extracted + ": it differs from " + resource
                                + " in alloy4.jar, which DynAlloy will extract again");
                    } else {
                        log.warn("Could not delete the outdated " + extracted + "; delete it by hand");
                    }
                }
            } catch (IOException e) {
                log.warn("Could not compare " + extracted + " with " + resource + ": " + e);
            }
        }
    }

    /** The directory DynAlloy extracts to, computed as {@code AlloyJNILibraryPath.alloyHome} does. */
    private static File extractionDirectory() {
        String user = System.getProperty("user.name");
        return new File(System.getProperty("java.io.tmpdir"), "alloy4tmp40-" + (user == null ? "" : user));
    }

    /** The jar folder DynAlloy copies from, computed as {@code AlloyJNILibraryPath.copyFromJAR} does. */
    private static String platformFolder() {
        String os = System.getProperty("os.name").toLowerCase(Locale.US).replace(' ', '-');
        if (os.startsWith("mac-")) {
            return "x86-mac";
        }
        if (os.startsWith("windows-")) {
            os = "windows";
        } else if (os.startsWith("linux")) {
            os = "linux";
        }
        String arch = System.getProperty("os.arch").toLowerCase(Locale.US).replace(' ', '-');
        if (arch.equals("powerpc")) {
            return "ppc-" + os;
        }
        return arch.replaceAll("\\Ai[3456]86\\z", "x86") + "-" + os;
    }

    private static byte[] read(InputStream in) throws IOException {
        if (in == null) {
            return null;
        }
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];
            int n;
            while ((n = in.read(buffer)) > 0) {
                out.write(buffer, 0, n);
            }
            return out.toByteArray();
        } finally {
            in.close();
        }
    }
}
