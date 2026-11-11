import java.util.*;

class Monitore
{
    ConcurrentList<Thread> waiting = new ConcurrentList<Thread>();
    Semaphore critical = new Semaphore();
    Semaphore self = new Semaphore();
    public Monitore()
    {

    }

    public void critical_enter(int id)
    {
        //while(!self.lock())
        //{
        //    Thread.yield();
        //}
        if(!critical.lock())
        {
            Thread thread = Thread.currentThread();
            waiting.add(thread);
            //System.out.println("Semaphore was locked! Putting thread to the queue. exited: " + id);
            thread.interrupt();
        }
        self.unlock();
    }

    public void critical_exit(int id)
    {
        //while(!self.lock())
        //{
        //    Thread.yield();
        //}
        Thread t = waiting.popIfNotEmpty(); 
        if(t == null)
        {
            //System.out.println("No threads in queue left. unlocking. exited: " + id);
            critical.unlock();
        }else
        {
            //System.out.println("Threads in queue left. Waking the next thread up. exited:" + id);
            t.interrupted();
        }
        self.lock();
    }
}