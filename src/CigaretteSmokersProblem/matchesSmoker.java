package CigaretteSmokersProblem;



public class matchesSmoker extends CigaretteSmokers implements Runnable {


	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				matchesSmokereSemaphore.acquire();
				if (matches == 0 && tobacco == 1 && paper == 1) {
					tobacco--;
					paper--;
					System.out.print("Matches smoker is smoking");
					for(int i = 0; i < 4; i++) {
						Thread.sleep(1000);
						System.out.print(".");
					}
					System.out.println();
					System.out.println("Matches smoker has smoked the cigarette.");
					System.out.println();
					Thread.sleep(2000);
					AgentSemaphore.release();
				} 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
