package Threads;


public class ThreadPoolContex implements Context {
    private final ThreadPool dummyThreadPool;

    public ThreadPoolContex(ThreadPool dummyThreadPool, Runnable[] runnables, Runnable callback) {
        this.dummyThreadPool = dummyThreadPool;
        dummyThreadPool.executeAll(runnables);
        while(!dummyThreadPool.isFinished()){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        callback.run();
    }

    @Override
    public int getCompletedTaskCount() {
        return dummyThreadPool.getCompletedTasks();
    }

    @Override
    public int getFailedTaskCount() {
        return dummyThreadPool.getFailedTasks();
    }

    @Override
    public int getInterruptedTaskCount() {
        return dummyThreadPool.getCancelledTasks();
    }

    @Override
    public void interrupt() {
        dummyThreadPool.interrupt();
    }

    @Override
    public boolean isFinished() {
        return dummyThreadPool.isFinished();
    }
}
