package CigaretteSmokersProblem;

public class paperSmoker extends CigaretteSmokers implements Runnable {

	
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			try {
				paperSmokerSemaphore.acquire();
				if (paper == 0 && tobacco == 1 && matches == 1) {
					tobacco--;
					matches--;
					System.out.print("Paper smoker is smoking");
					for(int i = 0; i < 4; i++) {
						Thread.sleep(1000);
						System.out.print(".");
					}
					System.out.println();
					System.out.println("Paper smoker has smoked the cigarette.");
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
