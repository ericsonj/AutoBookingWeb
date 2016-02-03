package test.containerlist;

import java.util.LinkedList;

/**
 *
 * @author ejoseph
 * @param <T>
 */
public interface ListObjects<T> {
     
    public LinkedList<T> getList();
    
    public void setList(LinkedList<T> list);
    
}
