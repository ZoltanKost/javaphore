class ListNode<T>
{
    ListNode<T> prev;
    ListNode<T> next;
    T data;
    public ListNode(T _data)
    {
        data = _data;
    }
    public T value()
    {
        return data;
    }
    public ListNode<T> getPrev()
    {
        return prev;
    }
    public ListNode<T> getNext()
    {
        return next;
    }
    public void setNext(ListNode<T> value)
    {
        next = value;
    }
    public void setPrev(ListNode<T> value)
    {
        prev = value;
    }
}