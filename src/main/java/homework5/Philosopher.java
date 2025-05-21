package homework5;

import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {
    private final int id;
    private final Semaphore leftFork;
    private final Semaphore rightFork;
    private final int eatCount;

    public Philosopher(int id, Semaphore leftFork, Semaphore rightFork, int eatCount) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.eatCount = eatCount;
    }

    @Override
    public void run() {
        for (int i = 0; i < eatCount; i++) {
            think();
            pickUpForks();
            eat(i + 1);
            putDownForks();
        }
        System.out.println("Философ " + (id + 1) + " завершил трапезу.");
    }

    private void think() {
        System.out.println("Философ " + (id + 1) + " размышляет...");
        sleepRandom();
    }

    private void eat(int mealNumber) {
        System.out.println("Философ " + (id + 1) + " ест (" + mealNumber + "/3)...");
        sleepRandom();
    }

    private void pickUpForks() {
        try {
            if (id % 2 == 0) {
                leftFork.acquire();
                rightFork.acquire();
            } else {
                rightFork.acquire();
                leftFork.acquire();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void putDownForks() {
        leftFork.release();
        rightFork.release();
    }

    private void sleepRandom() {
        try {
            Thread.sleep((int) (Math.random() * 1000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
