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
        if(!critical.try_lock()) // if didn't locked, someone is already there, enqueue
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
            critical.unlock(); // current thread is guaranteed THE only thread who can unlock it.
        }else
        {
            t.interrupted(); // unlock the poped thread
        }
    }
}