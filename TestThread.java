public class TestThread implements Runnable
{ 
    static Monitore mon = new Monitore(); 
    //static Semaphore sem = new Semaphore();
    static int thread_count;
    private int id;
    public static int i = 0;
    public TestThread()
    {
        id = thread_count;
        thread_count++;
    }

    public void run()
    {
        
        //System.out.println("Thread started " + id);
        mon.critical_enter(id);
        for(int j = 0; j < 10; j++) i++;
        mon.critical_exit(id);
        //System.out.println("Thread ended: " + id +" " +  i );
    }
}