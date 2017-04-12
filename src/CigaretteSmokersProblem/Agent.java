package CigaretteSmokersProblem;

public class Agent extends CigaretteSmokers implements Runnable {



	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				AgentSemaphore.acquire();
				int chosen = random.nextInt(3);
				if (chosen == 0) {
					paper++;
					tobacco++;
					combination = 1;
					System.out.println("Agent has left paper and tobacco on the table.");
				} else if (chosen == 1) {
					tobacco++;
					matches++;
					combination = 2;
					System.out.println("Agent has left tobacco and matches on the table.");
				} else {
					paper++;
					matches++;
					combination = 3;
					System.out.println("Agent has left paper and matches on the table.");
				}
				if (combination == 1) {
					matchesSmokereSemaphore.release();
				} else if (combination == 2) {
					paperSmokerSemaphore.release();
				} else {
					tobaccoSmokerSemaphore.release();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
