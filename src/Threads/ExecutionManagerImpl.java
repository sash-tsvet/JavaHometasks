package Threads;


public class ExecutionManagerImpl implements ExecutionManager {
    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        int coreNumber = Runtime.getRuntime().availableProcessors();
        return new ThreadPoolContex(new ThreadPool(coreNumber), tasks, callback);
    }
}
