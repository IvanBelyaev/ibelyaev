package ru.job4j.pack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Packer.
 * @author Ivan Belyaev
 * @since 18.04.2019
 * @version 1.0
 */
public class Packer {
    /** List of files for archiving. */
    private List<File> listOfFiles = new ArrayList<>();
    /** Arguments. */
    private Args args;

    /**
     * The constructor creates the object Packer.
     * @param args - arguments.
     *             format - -d "archived directory" -e "non archived files" -o "archive file name"
     */
    public Packer(Args args) {
        this.args = args;
    }

    /**
     * Entry point.
     * @param args - arguments.
     *             format - -d "archived directory" -e "non archived files" -o "archive file name"
     */
    public static void main(String[] args) {
        Args arguments = new Args(args);
        new Packer(arguments).packIn();
    }

    /**
     * Method creates archive.
     */
    private void packIn() {
        makeList();
        try (ZipOutputStream zout = new ZipOutputStream(
                new FileOutputStream(new File(args.directory(), args.output())))) {
            int startIndex = args.directory().lastIndexOf(File.separator);

            for (File file : listOfFiles) {
                String fileName = file.getAbsolutePath().substring(startIndex);
                if (file.isDirectory()) {
                    fileName = fileName + File.separator;
                }
                zout.putNextEntry(new ZipEntry(fileName));
                if (file.isFile()) {
                    try (FileInputStream fin = new FileInputStream(file)) {
                        byte[] buf = new byte[4096];
                        int len;
                        while ((len = fin.read(buf)) != -1) {
                            zout.write(buf, 0, len);
                        }
                    }
                }
                zout.closeEntry();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * The method fills the list of files for archiving.
     */
    public void makeList() {
        String exclude = args.exclude().replace("*", "");
        Queue<File> directories = new LinkedList<>();
        directories.offer(new File(args.directory()));
        while (!directories.isEmpty()) {
            for (File file : directories.poll().listFiles()) {
                if (file.isDirectory()) {
                    directories.offer(file);
                }
                if (!file.getName().contains(exclude)) {
                    listOfFiles.add(file);
                }
            }
        }
    }
}
