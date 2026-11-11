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
            //System.out.println("Semaphore is locked!");
            return false;
        }
        //.out.println("Semaphore successfully locked!");
        return true;
    }

    public void unlock()
    {
        locked.set(false);
        //System.out.println("Semaphore is unlocked!");
    }
}