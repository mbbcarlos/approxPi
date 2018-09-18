import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ParApproxPi extends Thread{

    private int simNr;
    private int inPoints;
    private double piEst;

    public ParApproxPi (int simNr) {
        this.simNr = simNr;
        this.inPoints = 0;
        this.piEst = 0;
    }

    public int getInPoints(){
        return inPoints;
    }

    public double getPiEstimation () {
        return this.piEst;
    }

    public void monteCarloSim () {

        for(int i = 0; i < this.simNr; i++){
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();

            if(x*x + y*y <= 1) inPoints++;
        }
        this.piEst = ((double) inPoints/simNr)*4;
    }

    public void run () {
        this.monteCarloSim();
    }

    public static void main ( String [] args ){
        if (args.length != 2) System.out.println("Invalid arguments. Please follow <program name> <simulation number>");


        int simulationNr = Integer.parseInt(args[0]);
        int threadNr = Integer.parseInt(args[1]);

        int pointsPerThread = simulationNr/threadNr;
        int [] inside = new int [threadNr];

        for (int i = 0; i < threadNr; i++) {
            ParApproxPi approxPi = new ParApproxPi(pointsPerThread);
            approxPi.monteCarloSim();
            inside[i] = approxPi.getInPoints();
        }

        int finalPoints = 0;
        for (int i = 0; i < threadNr; i++)
             finalPoints += inside[i];

        double finalPi = ((double) finalPoints/(threadNr*pointsPerThread)*4);

        System.out.println("Total number of points: " + pointsPerThread*threadNr);
        System.out.println("Points within circle: " + finalPoints);
        System.out.println("Pi estimation: " + finalPi);

    }

}
