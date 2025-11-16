package ThreadsPracticeWork4;

import java.util.ArrayList;
import java.util.List;

public class MiniWarehouse {

    private List<Shelf> shelves = new ArrayList<>();

    public void addBoxFirstFit(int size){
        for (int i = 0; i < shelves.size(); i++) {
            Shelf shelf = shelves.get(i);
            if(shelf.canFit(size)){
                shelf.putBox(size);
                break;
            }
        }
    }

    public void seeShelves(){
        for (Shelf shelf : shelves) {
            System.out.println(shelf);
        }
    }

    public void addShelves(Shelf shelf){
        shelves.add(shelf);
    }

}
