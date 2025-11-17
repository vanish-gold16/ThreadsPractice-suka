package ThreadsPracticeWork4;

public class Main {
    public static void main(String[] args) {

        MiniWarehouse miniWarehouse = new MiniWarehouse();

        Shelf shelf1 = new Shelf(20, 0);
        Shelf shelf2 = new Shelf(20, 0);
        Shelf shelf3 = new Shelf(20, 0);

        // miniWarehouse.addShelves(shelf1);
        // miniWarehouse.addShelves(shelf2);
        // miniWarehouse.addShelves(shelf3);

        int[] boxes = new int[]{5, 9, 7, 4, 12, 6};

        for (int i = 0; i < boxes.length; i++) {
            miniWarehouse.addBoxFirstFit(boxes[i]);
        }

        miniWarehouse.seeShelves();

    }
}
