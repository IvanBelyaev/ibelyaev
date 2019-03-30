package ru.job4j.io;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Search.
 * @author Ivan Belyaev
 * @since 30.03.2019
 * @version 1.0
 */
public class Search {
    /**
     * The method searches for all files with the specified extensions.
     * @param parent - search directoryю
     * @param exts - extensions.
     * @return a list of files with the specified extensions.
     */
    public List<File> files(String parent, List<String> exts) {
        File path = new File(parent);
        List<File> listOfFiles = new ArrayList<>();
        Queue<File> directories = new LinkedList<>();
        directories.offer(path);
        while (!directories.isEmpty()) {
            File dir = directories.poll();
            for (File file : dir.listFiles()) {
                if (file.isDirectory()) {
                    directories.offer(file);
                } else {
                    String fileName = file.getName();
                    String fileExt = fileName.substring(fileName.lastIndexOf('.') + 1);
                    if (exts.contains(fileExt)) {
                        listOfFiles.add(file);
                    }
                }
            }
        }
        return listOfFiles;
    }
}
