package ru.job4j.switcher;

/**
 * MasterSlaveBarrier.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 22.01.2021
 */
public class MasterSlaveBarrier {
    /** Was the previous thread a masterSlaveBarrier? */
    private boolean isPrevMaster = false;

    /**
     * Before master start.
     * @throws InterruptedException possible exception.
     */
    public synchronized void tryMaster() throws InterruptedException {
        while (isPrevMaster) {
            wait();
        }
    }

    /**
     * Before slave start.
     * @throws InterruptedException possible exception.
     */
    public synchronized void trySlave() throws InterruptedException {
        while (!isPrevMaster) {
            wait();
        }
    }

    /**
     * After master.
     */
    public synchronized void doneMaster() {
        isPrevMaster = true;
        notifyAll();
    }

    /**
     * After slave.
     */
    public synchronized void doneSlave() {
        isPrevMaster = false;
        notifyAll();
    }
}
