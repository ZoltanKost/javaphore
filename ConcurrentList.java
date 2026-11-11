public class ConcurrentList<T>
{
    Semaphore s;
    ListNode<T> head;
    ListNode<T> tail;
    int count;
    public ConcurrentList()
    {
        s = new Semaphore();
        head = new ListNode<T>(null);
        tail = new ListNode<T>(null);
        head.setNext(tail);
        tail.setPrev(head);
        count = 0;
    }

    public void add(T element)
    {
        s.lock();
        ListNode<T> node = new ListNode<T>(element);
        ListNode<T> prev = tail.getPrev();
        prev.setNext(node);
        tail.setPrev(node);
        node.setNext(tail);
        node.setPrev(prev);
        count++;
        s.unlock();
    }
    public int size()
    {
        return count;
    }
    private T remove(int index)
    {
        if (index >= count)
        {
            return null;
        }
        ListNode<T> current = head.getNext();
        for(int i = 0; i < index; i++)
        {
            current = current.getNext();
        }
        ListNode<T> prev = current.getPrev();
        ListNode<T> next = current.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        count--;
        return current.value();
    }
    public T get(int id)
    {
        s.lock();
        if(id >= count) 
        {
            s.unlock();
            return null;
        }
        ListNode<T> current = head.getNext();
        int i = 0;
        while(i != id)
        {
            current = current.getNext();
            i++;
        }
        s.unlock();
        return current.value();
    }

    public T popIfNotEmpty()
    {
        s.lock();
        T result;
        if(count > 0)
        {
            result = remove(0);
        }else
        {
            result = null;
        }
        s.unlock();
        return result;
    }
}