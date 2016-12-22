package Threads;


public class ExecutorManagerImpl implements ExecutionManager {
    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        int coreNumber = Runtime.getRuntime().availableProcessors();
        return new ThreadPoolContex(new DummyThreadPool(coreNumber), tasks);
    }
}
