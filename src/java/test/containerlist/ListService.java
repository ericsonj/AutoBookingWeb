package test.containerlist;

import java.util.LinkedList;
import test.elements.Service;

/**
 *
 * @author ejoseph
 */
public class ListService implements ListObjects<Service>{

    private LinkedList<Service> list= new LinkedList<>();
    
    @Override
    public LinkedList<Service> getList() {
        return list;
    }

    @Override
    public void setList(LinkedList<Service> list) {
        this.list = list;
    }
     
}
