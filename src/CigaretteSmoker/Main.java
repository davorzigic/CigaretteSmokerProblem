package CigaretteSmoker;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Main {

	static int paper, tobacco, matches = 0;
	static Random random = new Random();
	static int pickOne;

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
						} else if (paper == 0 & (tobacco == 0 || matches == 0)) {
							paper++;
							System.out.println("Smoker 1 has left the paper on the table.");
						} else if (paper == 1) {
							pickOne = random.nextInt(2);
							if (pickOne == 0) {
								s2.release();
							} else {
								s3.release();
							}
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
						} else if (tobacco == 0 & (paper == 0 || matches == 0)) {
							tobacco++;
							System.out.println("Smoker 1 has left the tobacco on the table.");
						} else if (tobacco == 1) {
							pickOne = random.nextInt(2);
							if (pickOne == 0) {
								s1.release();
							} else {
								s3.release();
							}
						}

						Thread.sleep(500);
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
							Thread.sleep(2000);
							System.out.println("Smoker 3 has smoked the cigarette.");
							s4.release();
						} else if (matches == 0 & (tobacco == 0 || paper == 0)) {
							matches++;
							System.out.println("Smoker 3 has left the matches on the table.");
						} else if (matches == 1) {
							pickOne = random.nextInt(2);
							if (pickOne == 0) {
								s1.release();
							} else {
								s2.release();
							}
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
							System.out.println("Agent has left paper and tobacco on the table.");
						} else if (chosen == 1) {
							tobacco++;
							matches++;
							System.out.println("Agent has left tobacco and matches on the table.");
						} else {
							paper++;
							matches++;
							System.out.println("Agent has left paper and matches on the table.");
						}
						int pickOne = random.nextInt(3);
						if (pickOne == 0) {
							s1.release();
						} else if (pickOne == 1) {
							s2.release();
						} else {
							s3.release();
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
