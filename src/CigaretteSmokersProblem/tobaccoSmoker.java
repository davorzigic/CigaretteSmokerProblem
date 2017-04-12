package CigaretteSmokersProblem;



public class tobaccoSmoker extends CigaretteSmokers implements Runnable {


	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			try {
				tobaccoSmokerSemaphore.acquire();

				if (tobacco == 0 && paper == 1 && matches == 1) {
					paper--;
					matches--;
					System.out.print("Tobacco smoker is smoking");
					for(int i = 0; i < 4; i++) {
						Thread.sleep(1000);
						System.out.print(".");
					}
					System.out.println();
					System.out.println("Tobacco smoker has smoked the cigarette.");
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
