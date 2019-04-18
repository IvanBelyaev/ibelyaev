package ru.job4j.pack;

/**
 * Args.
 * @author Ivan Belyaev
 * @since 18.04.2019
 * @version 1.0
 */
public class Args {
    /** Archived Directory. */
    private String directory;
    /** Non archived files.  */
    private String exclude;
    /** Archive file name. */
    private String output;

    /**
     * The constructor creates the object Args.
     * @param args - arguments.
     *             format - -d "archived directory" -e "non archived files" -o "archive file name"
     */
    public Args(String[] args) {
        if (args.length != 6) {
            throw new IllegalArgumentException("Wrong number of arguments.");
        }

        if ("-d".equals(args[0]) && "-e".equals(args[2]) && "-o".equals(args[4])) {
            this.directory = args[1];
            this.exclude = args[3];
            this.output = args[5];
        } else {
            throw new IllegalArgumentException("Wrong parameters.");
        }
    }

    /**
     * Method returns archived directory.
     * @return archived directory.
     */
    public String directory() {
        return this.directory;
    }

    /**
     * Method returns non archived files.
     * @return non archived files.
     */
    public String exclude() {
        return this.exclude;
    }

    /**
     * Method returns archive file name.
     * @return archive file name.
     */
    public String output() {
        return this.output;
    }
}
