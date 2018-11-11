/**
 * Semaphore.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

/**
 * Program to implement a semaphore
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

/**
 * how should the working be
 * consumer should consume only when items are full
 *
 * if 3 producers then all 3 should produce simultaneously
 */


public class Semaphore extends Thread {
    int semaphore_num;

    /**
     * Constructor
     * @param semaphore_num : number of permits available
     */
    public Semaphore(int semaphore_num) {
        this.semaphore_num = semaphore_num;
    }

    /**
     * Try to acquire lock if permits available else wait
     */
    public synchronized void acquire(int num_acquire) {
        if (semaphore_num - num_acquire < 0) {
            try {
                wait();
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        semaphore_num -= num_acquire;
    }

    /**
     * Release lock and increment available permits
     */
    public synchronized void release(int num_release) {
        semaphore_num += num_release;
        if (semaphore_num > 0) {
            notify();
        }
    }

    /**
     * Return number of permits
     */
    public int availablePermits() {
        return semaphore_num;
    }

}