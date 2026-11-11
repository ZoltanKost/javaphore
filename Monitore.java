import java.util.*;

class Monitore
{
    ConcurrentList<Thread> waiting = new ConcurrentList<Thread>();
    Semaphore critical = new Semaphore();
    public Monitore()
    {

    }

    public void critical_enter(int id)
    {
        if(!critical.lock())
        {
            Thread thread = Thread.currentThread();
            waiting.add(thread);
            thread.interrupt();
        }
    }

    public void critical_exit(int id)
    {
        Thread t = waiting.popIfNotEmpty(); 
        if(t == null)
        {
            critical.unlock();
        }else
        {
            t.interrupted();
        }
    }
}