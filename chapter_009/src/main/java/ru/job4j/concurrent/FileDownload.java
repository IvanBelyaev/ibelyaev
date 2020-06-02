package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Paths;

/**
 * FileDownload.
 * Simple wget equivalent.
 * @author Ivan Belyaev
 * @version 1.0
 * @since 13.05.2020
 */
public class FileDownload implements Runnable {
    /** File address. */
    private final String file;
    /** Speed in kilobytes per second. */
    private final int speed;

    /**
     * Constructor.
     * @param file file address.
     * @param speed speed in kilobytes per second.
     */
    public FileDownload(String file, int speed) {
        this.file = file;
        this.speed = speed;
    }

    /**
     * Download a file at a given speed.
     */
    @Override
    public void run() {
        try {
            String outputFileName = URLDecoder.decode(Paths.get(file).getFileName().toString(), "UTF-8");
            try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream(
                         System.getProperty("java.io.tmpdir") + File.separator + outputFileName
                 )
            ) {
                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                long bytesDownload = 0;
                long startTime = System.currentTimeMillis();
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    bytesDownload += bytesRead;
                    long sleepTime = bytesDownload / speed - elapsedTime;
                    if (sleepTime < 0) {
                        sleepTime = 0;
                    }
                    System.out.printf(
                            "\rLoading... %f kB/s",
                            elapsedTime == 0 ? 0 : (double) bytesDownload / (elapsedTime + sleepTime)
                    );
                    Thread.sleep(sleepTime);
                }
                System.out.println("\nLoading complete!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Entry point.
     * @param args command line arguments.
     *             Used with two parameters.
     *             wget [url] [speed]
     *             url - file address.
     *             speed - speed in kilobytes per second.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("wget [url] [speed (kB/s)]");
        } else {
            String file = args[0];
            int speed = Integer.parseInt(args[1]);
            new Thread(new FileDownload(file, speed)).start();
        }
    }
}
