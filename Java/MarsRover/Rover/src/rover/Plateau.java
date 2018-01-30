
package rover;

/**
 *
 * @author keegz_000
 */
import java.util.Scanner;

class Plateau {

    //Variables
    private int size;

    public Plateau() {

        Scanner in = new Scanner(System.in);

        System.out.println("Enter the Size of the plateau. Eg.5");
        size=in.nextInt();
        in.nextLine();

    }

    public Plateau(int sz){

        size=sz;

    }

    public Cell getNeighbour(Rover.Direction direct,Cell cell){

        Cell neighbour = null;
        //Faces the rover in the specified direction
        switch (direct) {

            case N:
                neighbour=new Cell(cell.x,cell.y+1);
                break;
            case W:
                neighbour=new Cell(cell.x-1,cell.y);
                break;
            case S:
                neighbour=new Cell(cell.x,cell.y-1);
                break;
            case E:
                neighbour=new Cell(cell.x+1,cell.y);
                break;
        }

        if((neighbour.x>=0)&&(neighbour.y>=0)&&(neighbour.x<size)&&(neighbour.y<size))
            return neighbour;

        return null;
    }

}
