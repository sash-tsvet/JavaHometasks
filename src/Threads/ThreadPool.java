package Threads;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {
    private final int threadNum;
    private final LinkedList<Runnable> workQueue;
    private final ThreadWorker[] threadWorkers;

    private AtomicInteger completedTasks = new AtomicInteger();
    private AtomicInteger failedTasks = new AtomicInteger();
    private volatile int cancelledTasks;
    private int tasksNum;

    public ThreadPool(int threadNum) {
        this.threadNum = threadNum;
        workQueue = new LinkedList<Runnable>();

        this.threadWorkers = new ThreadWorker[threadNum];
        for (int i = 0; i < threadNum; ++i) {
            threadWorkers[i] = new ThreadWorker();
            threadWorkers[i].start();
        }
    }

    public void executeAll(Runnable[] runnables) {
        tasksNum = runnables.length;
        synchronized (workQueue) {
            for (Runnable r : runnables) {
                workQueue.add(r);
            }
            workQueue.notifyAll();
        }
    }

    public void execute(Runnable runnable) {
        synchronized (workQueue) {
            workQueue.addLast(runnable);
            workQueue.notify();
        }
    }

    public int getCompletedTasks() {
        return completedTasks.get();
    }

    public int getFailedTasks() {
        return failedTasks.get();
    }

    public void interrupt() {
        synchronized (workQueue) {
            cancelledTasks = workQueue.size();
            workQueue.clear();
        }
    }

    public int getCancelledTasks() {
        return cancelledTasks;
    }

    public boolean isFinished() {
        return tasksNum == cancelledTasks || tasksNum == completedTasks.get();
    }


    class ThreadWorker extends Thread {
        public void run() {
            Runnable r;
            while (true) {
                synchronized (workQueue) {
                    if (workQueue.isEmpty()) {
                        try {
                            workQueue.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    r = workQueue.removeFirst();
                }
                try {
                    r.run();
                    completedTasks.getAndIncrement();
                } catch (RuntimeException e) {
                    failedTasks.getAndIncrement();
                }
            }
        }
    }
    public static void main(String[] args) {
        Runner[] runners = new Runner[14];
        for (int i=0; i <14; i++){
           runners[i] = new Runner(i);
        }

        ExecutionManager manager = new ExecutionManagerImpl();

        manager.execute(new Exiter(), runners);

    }

}

class Runner implements Runnable {

    Integer number;

    public Runner(Integer number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println(number);
    }
}

class Exiter implements Runnable {
    @Override
    public void run() {
        System.exit(0);
    }
}