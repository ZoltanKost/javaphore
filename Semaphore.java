import java.util.concurrent.atomic.AtomicBoolean;

public class Semaphore
{
    private AtomicBoolean locked; 

    public Semaphore()
    {
        locked = new AtomicBoolean(false);
    }

    public boolean try_lock()
    {
        while(!locked.compareAndSet(false,true))
        {
            return false; // just immediatly return, let the caller know that he should go sleep
        }
        return true;
    }

    public void lock()
    {
        while(!locked.compareAndSet(false,true))
        {
            Thread.yield(); // wait until it's free to lock!
        }
    }

    public void unlock()
    {
        locked.set(false);
    }
}