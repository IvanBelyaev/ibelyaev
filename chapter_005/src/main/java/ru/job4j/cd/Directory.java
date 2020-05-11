package ru.job4j.cd;

/*
 * Please solve the following puzzle which simulates generic directory structures.
 * The solution should be directory agnostic.
 * Be succinct yet readable. You should not exceed more than 200 lines.
 * Consider adding comments and asserts to help the understanding.
 * Code can be compiled with javac Directory.java
 * Code should be executed as: java -ea Directory (-ea option it's to enabled the assert)
 */

import java.io.File;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Shell.
 */
class Shell {
    /** Storage path. */
    private Deque<String> absolutePath = new LinkedList<>();

    /**
     * Emulates a directory change.
     * @param path new directory.
     * @return the same Shell object.
     */
    Shell cd(final String path) {
        if (path.startsWith(File.separator)) {
            absolutePath.clear();
        }
        addToPath(path);
        return this;
    }

    /**
     * Converts a path to a string.
     * @return path as string.
     */
    public String path() {
        StringBuilder sb = new StringBuilder();
        for (String s : absolutePath) {
            sb.append(File.separator);
            sb.append(s);
        }
        if (sb.length() == 0) {
            sb.append(File.separator);
        }
        return sb.toString();
    }

    /**
     * Adds a relative path to an existing one.
     * @param path new path.
     */
    private void addToPath(String path) {
        for (String segment : path.split(File.separator)) {
            if (segment.isEmpty() || ".".equals(segment)) {
                continue;
            } else if ("..".equals(segment)) {
                absolutePath.pollLast();
                continue;
            }
            absolutePath.addLast(segment);
        }
    }
}

/**
 * Class for tests.
 */
public class Directory {
    /**
     * Entry point.
     * @param args command line arguments. Not used.
     */
    public static void main(String[] args) {

        final Shell shell = new Shell();
        assert shell.path().equals("/");

        shell.cd("/");
        assert shell.path().equals("/");

        shell.cd("usr/..");
        assert shell.path().equals("/");

        shell.cd("usr").cd("local");
        shell.cd("../local").cd("./");
        assert shell.path().equals("/usr/local");

        shell.cd("..");
        assert shell.path().equals("/usr");

        shell.cd("//lib///");
        assert shell.path().equals("/lib");
    }
}