public class TestThread implements Runnable
{ 
    static Monitore mon = new Monitore(); 
    private int id;
    public static int i = 0;
    public TestThread()
    {
    }

    public void run()
    {
        
        mon.critical_enter(id);
        for(int j = 0; j < 10; j++) i++;
        mon.critical_exit(id);
    }
}