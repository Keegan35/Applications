
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author keegz_000
 */
public class RoverTest {
    
    public RoverTest() {
    }

    Rover.Direction Direct;
    Plateau plateau = new Plateau(5);
    Rover rov = new Rover();

    @Test
    public void testRotation() {


        //Assertion For LeftRotation.

        Direct= Rover.Direction.N;
        assertEquals(Rover.Direction.W, rov.setDirection(Direct, 'L'));

        Direct= Rover.Direction.W;
        assertEquals(Rover.Direction.S, rov.setDirection(Direct, 'L'));

        Direct= Rover.Direction.S;
        assertEquals(Rover.Direction.E, rov.setDirection(Direct, 'L'));

        Direct= Rover.Direction.E;
        assertEquals(Rover.Direction.N, rov.setDirection(Direct, 'L'));

        //Assertion for RightRotation

        Direct= Rover.Direction.N;
        assertEquals(Rover.Direction.E, rov.setDirection(Direct, 'R'));

        Direct= Rover.Direction.W;
        assertEquals(Rover.Direction.N, rov.setDirection(Direct, 'R'));

        Direct= Rover.Direction.S;
        assertEquals(Rover.Direction.W, rov.setDirection(Direct, 'R'));

        Direct= Rover.Direction.E;
        assertEquals(Rover.Direction.S, rov.setDirection(Direct, 'R'));

    }

    @Test
    public void testCheckMovement(){

        //Impossible Moves
        for(int i=0;i<5;i++) {

            Direct = Rover.Direction.W;
            assertEquals(null,plateau.getNeighbour(DirIp, new Cell(0,i)));

            Direct=Rover.Direction.S;
            assertEquals(null,plateau.getNeighbour(DirIp,new Cell(i,0)));

        }

        for(int i=0;i<5;i++){

            Direct=Rover.Direction.N;
            assertEquals(null,plateau.getNeighbour(DirIp,new Cell(i,4)));

            Direct=Rover.Direction.E;
            assertEquals(null,plateau.getNeighbour(DirIp,new Cell(4,i)));

        }

        //Take a cell from the center and check its moves

        Cell centerCell= new Cell(2,3);
        Direct=Rover.Direction.E;

        Cell next=plateau.getNeighbour(Direct,centerCell);

        assertEquals(3,next.x);
        assertEquals(3,next.y);

        Direct=Rover.Direction.N;
        next=plateau.getNeighbour(Direct,centerCell);
        assertEquals(2,next.x);
        assertEquals(4,next.y);

        Direct=Rover.Direction.W;
        next=plateau.getNeighbour(Direct,centerCell);
        assertEquals(1,next.x);
        assertEquals(3,next.y);

        Direct=Rover.Direction.S;
        next=plateau.getNeighbour(Direct,centerCell);
        assertEquals(2,next.x);
        assertEquals(2,next.y);

    }
}

