package rover;
/**
 *
 * @author keegz_000
 */

public class Rover {

    //Variables
    private String inst_set;
    private Plateau plateau;
    private Cell curLocation;

    public enum Direction{N,S,E,W}

    Direction direction;

    public Rover(Plateau p1at, int x, int y, Direction direct, String instSet) {

        plateau=p1at;
        inst_set=instSet;
        this.direction=direct;
        curLocation=new Cell(x, y);

    }

    public Rover(){}

    public void executeInst() {
        //When M is entered the rover will move in the direction it is facing
        for(char inst: inst_set.toCharArray()){

            if (inst == 'M') {

                Cell nextCell = plateau.getNeighbour(direction, curLocation);

                if (nextCell != null)
                    curLocation = nextCell;

                else
                    System.out.println("This move is not possible. Going to next Instruction");

            }
            //When L or R is entered the rover will turn
            else if (inst == 'L' || inst == 'R') {

                direction = setDirection(direction, inst);

            }
            else {
                throw new IllegalArgumentException("Bad instruction " + inst);
            }
        }
    }

    public void showCurrentLocation(){
        //current location of rover
        System.out.println("Current Cell is:");
        System.out.print(curLocation.x);
        System.out.print("\t"+curLocation.y);
        System.out.print("\t"+direction+"\n");

    }

    //Control direction of rover
    public Direction setDirection(Direction direct, char inst) {

        if(inst=='L') {
            switch (direction) {
                case N:
                    direct = Direction.W;
                    break;
                case W:
                    direct = Direction.S;
                    break;
                case S:
                    direct = Direction.E;
                    break;
                case E:
                    direct = Direction.N;
                    break;
            }
        }

        else if(inst=='R'){

            switch (direct) {
                case N:
                    direct = Direction.E;
                    break;
                case W:
                    direct = Direction.N;
                    break;
                case S:
                    direct = Direction.W;
                    break;
                case E:
                    direct = Direction.S;
                    break;
            }
        }

        return direct;

    }
}
