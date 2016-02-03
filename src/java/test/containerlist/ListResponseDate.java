package test.containerlist;

import java.util.LinkedList;
import test.elements.ResponseDate;

/**
 *
 * @author ejoseph
 */
public class ListResponseDate implements ListObjects<ResponseDate>{
    
    LinkedList<ResponseDate> list = new LinkedList<>();
    
    @Override
    public LinkedList<ResponseDate> getList() {
        return list;
    }

    @Override
    public void setList(LinkedList<ResponseDate> list) {
        this.list = list;
    }
    
}
