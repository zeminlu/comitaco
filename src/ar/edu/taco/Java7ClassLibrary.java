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

import java.io.File;

/**
 * The Java 7 class library that the JML tools read.
 *
 * <p>The JML parser (JML2/MultiJava, {@code lib/jml-release.jar}) and the runtime assertion
 * checker JML4C ({@code lib/stryker/jml4c.jar}) resolve JDK types by reading the JDK's class
 * files, and they cannot use the class library of Java 8 or later (they do read the subjects'
 * classes compiled for Java 8, as IntelliJ compiles them). On Java 7 they read the running JVM's
 * own class library. On Java 8 and 21 they, and the JDT parser used for variabilization, are
 * given a Java 7 {@code rt.jar} instead: the one named by the {@value #PROPERTY} system property,
 * or {@value #DEFAULT_LOCATION} under the working directory, where
 * {@code scripts/fetch-java7-classlib.sh} puts it. Only Java 7, 8 and 21 are supported.
 */
public final class Java7ClassLibrary {

    /** System property naming a Java 7 {@code rt.jar}; ignored on Java 7. */
    public static final String PROPERTY = "stryker.classlib";

    /** Where the {@code rt.jar} is looked for when {@value #PROPERTY} is not set, relative to the working directory. */
    public static final String DEFAULT_LOCATION = "lib/java7/rt.jar";

    private Java7ClassLibrary() {
    }

    /** Whether the running JVM is Java 7, in which case the JML tools use its own class library. */
    public static boolean runningOnJava7() {
        return "1.7".equals(System.getProperty("java.specification.version"));
    }

    /**
     * The Java 7 {@code rt.jar} to use: on Java 7, the JVM's own; otherwise the {@value #PROPERTY}
     * system property if set, or {@value #DEFAULT_LOCATION} under {@code user.dir}. The file is
     * not checked here; see {@link #check()}.
     */
    public static String path() {
        if (runningOnJava7()) {
            return System.getProperty("java.home") + File.separator + "lib" + File.separator + "rt.jar";
        }
        String configured = System.getProperty(PROPERTY);
        if (configured != null && !configured.isEmpty()) {
            return configured;
        }
        return System.getProperty("user.dir") + File.separator + DEFAULT_LOCATION;
    }

    /**
     * {@code classPath} without the entries that belong to the running JDK (files under its
     * installation directory). Some launchers, IntelliJ among them, put the JDK's own jars, its
     * {@code rt.jar} included, on the class path of the programs they run; the JML tools, which
     * are handed {@code java.class.path}, would then find the running JDK's classes there despite
     * {@link #path()}. On Java 7 the class path is returned unchanged.
     *
     * @param classPath a class path, entries separated by {@link File#pathSeparator}
     * @return the class path without the running JDK's entries
     */
    public static String withoutJdkEntries(String classPath) {
        if (runningOnJava7() || classPath == null) {
            return classPath;
        }
        File javaHome = new File(System.getProperty("java.home")).getAbsoluteFile();
        // On Java 8, java.home is the JDK's jre/ directory, and lib/tools.jar & co. are next to it.
        File jdk = "jre".equals(javaHome.getName()) ? javaHome.getParentFile() : javaHome;
        String jdkPrefix = canonicalPath(jdk) + File.separator;
        StringBuilder kept = new StringBuilder();
        for (String entry : classPath.split(File.pathSeparator, -1)) {
            if (!entry.isEmpty() && canonicalPath(new File(entry)).startsWith(jdkPrefix)) {
                continue;
            }
            if (kept.length() > 0) {
                kept.append(File.pathSeparator);
            }
            kept.append(entry);
        }
        return kept.toString();
    }

    private static String canonicalPath(File file) {
        try {
            return file.getCanonicalPath();
        } catch (java.io.IOException e) {
            return file.getAbsolutePath();
        }
    }

    /**
     * Fails unless the JVM is Java 7, or Java 8 or 21 with a Java 7 class library at {@link #path()}.
     *
     * @throws IllegalStateException with instructions, if that is not the case
     */
    public static void check() {
        String version = System.getProperty("java.specification.version");
        if ("1.7".equals(version)) {
            return;
        }
        if (!"1.8".equals(version) && !"21".equals(version)) {
            throw new IllegalStateException("TACO and Stryker run on Java 7, 8 or 21, but this is Java "
                    + version + " (" + System.getProperty("java.home") + "). See docs/BUILDING.md.");
        }
        if (!new File(path()).isFile()) {
            throw new IllegalStateException("On Java " + version + ", TACO and Stryker need a Java 7 class library, and "
                    + path() + " does not exist. Run scripts/fetch-java7-classlib.sh, or set -D" + PROPERTY
                    + " to a Java 7 rt.jar. See docs/BUILDING.md.");
        }
    }
}
