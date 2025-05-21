package homework5;

import java.util.concurrent.Semaphore;

public class DiningPhilosophers {
    public static void main(String[] args) {
        final int PHIL_COUNT = 5;
        final int EAT_TIMES = 3;
        Philosopher[] philosophers = new Philosopher[PHIL_COUNT];
        Semaphore[] forks = new Semaphore[PHIL_COUNT];

        for (int i = 0; i < PHIL_COUNT; i++) {
            forks[i] = new Semaphore(1);
        }

        for (int i = 0; i < PHIL_COUNT; i++) {
            Semaphore leftFork = forks[i];
            Semaphore rightFork = forks[(i + 1) % PHIL_COUNT];
            philosophers[i] = new Philosopher(i, leftFork, rightFork, EAT_TIMES);
        }

        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }

        for (Philosopher philosopher : philosophers) {
            try {
                philosopher.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Все философы завершили еду.");
    }
}
