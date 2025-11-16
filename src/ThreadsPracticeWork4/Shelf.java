package ThreadsPracticeWork4;

public class Shelf {

    private int capacity;
    private int currentLoad;

    public void putBox(int size){
        setCurrentLoad(getCurrentLoad() + size);
    }

    public boolean canFit(int size){
        return currentLoad + size <= capacity;
    }

    @Override
    public String toString() {
        return "Shelf: max capacity " + capacity + ", current load " + currentLoad;
    }

    public Shelf(int capacity, int currentLoad) {
        this.capacity = capacity;
        this.currentLoad = currentLoad;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(int currentLoad) {
        this.currentLoad = currentLoad;
    }
}
