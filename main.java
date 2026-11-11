import java.util.*;

class Main
{
    final static int SIZE = 10000;
    static Runnable[] runs = new Runnable[SIZE];
    static Thread[] treads = new Thread[SIZE];
    static int size = 0;
    public static void main() throws Exception
    {
        System.console().readLine("Press: ");
        System.out.println("count,"+ "time");
        for(int i = 0; i < 10000; i++)
        {
            analyse(i);
        }
    }

    public static void analyse(int count) throws Exception
    {
        TestThread.i = 0;
        while(size!=count)
        {
            runs[size++] = new TestThread();
        }
        for (int i = 0; i < size; i++)
        {
            treads[i] = new Thread(runs[i]);
        }

        long start = System.nanoTime();
        for(int i = 0; i < count; i++)
        {
            treads[i].start();
        }
        for(int i = 0; i < size; i++)
        {
            treads[i].join();
        }
        long end = System.nanoTime();
        
        long dt = end - start;
        System.out.println(count + ","+ dt / 1_000_000.0 + "," + TestThread.i);
    }
}