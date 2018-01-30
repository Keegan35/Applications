package rover;

import java.util.Scanner;
/**
 *
 * @author keegz_000
 */
public class Main {
    
    public static void main(String... a){

        Plateau p1ateau = new Plateau();
        Scanner in = new Scanner(System.in);

        int x,y;
        String instSet;

        System.out.println("Enter the direction the rover is facing. Eg.N");
        String direct = in.nextLine();

        System.out.println("Enter the Initial XLocation:");
        x=in.nextInt();
        in.nextLine();

        System.out.println("Enter the Initial YLocation:");
        y=in.nextInt();
        in.nextLine();

        System.out.println("Enter the Instructions. Eg.R/M/L");
        instSet=in.nextLine();

        Rover rov=new Rover(p1ateau,x,y,Rover.Direction.valueOf(direct),instSet);
        rov.executeInst();
        rov.showCurrentLocation();

    }
}
