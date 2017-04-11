package CigaretteSmokerV3;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class MainV3 {

	static int paper, tobacco, matches = 0;
	static Random random = new Random();
	static int pickOne, combination;

	static Semaphore s1 = new Semaphore(0);
	static Semaphore s2 = new Semaphore(0);
	static Semaphore s3 = new Semaphore(0);
	static Semaphore s4 = new Semaphore(1);

	public static void main(String[] args) throws Exception {

		Thread paperThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {

					try {
						s1.acquire();
						if (paper == 0 && tobacco == 1 && matches == 1) {
							tobacco--;
							matches--;
							Thread.sleep(2000);
							System.out.println("Smoker 1 has smoked the cigarette.");
							s4.release();
						} 

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});

		Thread tobaccoThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {

					try {
						s2.acquire();

						if (tobacco == 0 && paper == 1 && matches == 1) {
							paper--;
							matches--;
							Thread.sleep(2000);
							System.out.println("Smoker 2 has smoked the cigarette.");
							s4.release();
						} 

						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});

		Thread matchesThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						s3.acquire();
						if (matches == 0 && tobacco == 1 && paper == 1) {
							tobacco--;
							paper--;
							System.out.println("Smoker 3 is smoking ... ");
							Thread.sleep(2000);
							System.out.println("Smoker 3 has smoked the cigarette.");
							s4.release();
						} 
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});

		Thread agentThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						s4.acquire();
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
							s3.release();
						} else if (combination == 2) {
							s1.release();
						} else {
							s2.release();
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		paperThread.start();
		tobaccoThread.start();
		matchesThread.start();
		agentThread.start();
	}
}
