package test.containerlist;

import java.util.LinkedList;
import test.elements.ResponseAvailableHour;

/**
 *
 * @author ejoseph
 */
public class ListResponseAHour implements ListObjects<ResponseAvailableHour>{

    LinkedList<ResponseAvailableHour> list = new LinkedList<>();
    
    @Override
    public LinkedList<ResponseAvailableHour> getList() {
        return list;
    }

    @Override
    public void setList(LinkedList<ResponseAvailableHour> list) {
        this.list = list;
    }
    
    
}
