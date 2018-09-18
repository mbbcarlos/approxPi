import java.util.Random;

public class SeqApproxPi {

    private int simNr;
    private int inPoints;
    private double piEst;

    public SeqApproxPi (int simNr) {
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

         Random random = new Random();

        for(int i = 0; i < this.simNr; i++){
            double x = random.nextDouble();
            double y = random.nextDouble();

            if(x*x + y*y <= 1) inPoints++;
        }
        this.piEst = ((double) inPoints/simNr)*4;
    }


    public static void main ( String [] args ){
        if (args.length != 1) System.out.println("Invalid arguments. Please follow <program name> <simulation number>");


        int simulationNr = Integer.parseInt(args[0]);

        SeqApproxPi approxPi = new SeqApproxPi(simulationNr);
        approxPi.monteCarloSim();

        System.out.println("Total number of points: " + simulationNr);
        System.out.println("Points within circle: " + approxPi.getInPoints());
        System.out.println("Pi estimation: " + approxPi.getPiEstimation());

    }


}
