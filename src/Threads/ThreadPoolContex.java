package Threads;


public class ThreadPoolContex implements Context {
    private final DummyThreadPool dummyThreadPool;

    public ThreadPoolContex(DummyThreadPool dummyThreadPool, Runnable[] runnables) {
        this.dummyThreadPool = dummyThreadPool;
        dummyThreadPool.executeAll(runnables);
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
