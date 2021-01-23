package ru.job4j.switcher;

/**
 * Switcher.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 22.01.2021
 */
public class Switcher {
    /**
     * Entry point.
     * @param args command line argument. Not used.
     * @throws InterruptedException possible exception.
     */
    public static void main(String[] args) throws InterruptedException {
        MasterSlaveBarrier masterSlaveBarrier = new MasterSlaveBarrier();
        Thread first = new Thread(
                () -> {
                    while (true) {
                        try {
                            masterSlaveBarrier.tryMaster();
                            System.out.println("Thread A");
                            masterSlaveBarrier.doneMaster();
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    while (true) {
                        try {
                            masterSlaveBarrier.trySlave();
                            System.out.println("Thread B");
                            masterSlaveBarrier.doneSlave();
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }
}
