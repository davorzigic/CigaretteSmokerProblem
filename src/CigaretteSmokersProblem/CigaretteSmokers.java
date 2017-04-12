package CigaretteSmokersProblem;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class CigaretteSmokers {

	public static int tobacco = 0;
	public static int paper = 0;
	public static int matches = 0;
	
		
	static Random random = new Random();
	static int pickOne, combination;

	static Semaphore tobaccoSmokerSemaphore = new Semaphore(0);
	static Semaphore paperSmokerSemaphore = new Semaphore(0);
	static Semaphore matchesSmokereSemaphore = new Semaphore(0);
	static Semaphore AgentSemaphore = new Semaphore(1);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		tobaccoSmoker tobacco = new tobaccoSmoker();
		paperSmoker paper = new paperSmoker();
		matchesSmoker matches = new matchesSmoker();
		Agent agent = new Agent();

		Thread tobaccoThread = new Thread(tobacco);
		Thread paperThread = new Thread(paper);
		Thread matchesThread = new Thread(matches);
		Thread agentThread = new Thread(agent);
		
		tobaccoThread.start();
		paperThread.start();
		matchesThread.start();
		agentThread.start();

	}

}
