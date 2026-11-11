import java.util.concurrent.atomic.AtomicBoolean;

public class Semaphore
{
    private AtomicBoolean locked;

    public Semaphore()
    {
        locked = new AtomicBoolean(false);
    }

    public boolean lock()
    {
        while(!locked.compareAndSet(false,true))
        {
            return false;
        }
        return true;
    }

    public void unlock()
    {
        locked.set(false);
    }
}