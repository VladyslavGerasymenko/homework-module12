package task2;

import java.util.concurrent.*;

public class FizzBuzz {
    private final int n;
    private int currentNumber;
    private final Semaphore fizzSemaphore;
    private final Semaphore buzzSemaphore;
    private final Semaphore fizzBuzzSemaphore;
    private final Semaphore numberSemaphore;

    public FizzBuzz(int n) {
        this.n = n;
        this.currentNumber = 1;
        this.fizzSemaphore = new Semaphore(0);
        this.buzzSemaphore = new Semaphore(0);
        this.fizzBuzzSemaphore = new Semaphore(0);
        this.numberSemaphore = new Semaphore(1);
    }

    public void fizz() throws InterruptedException {
        while (true) {
            fizzSemaphore.acquire();
            if (currentNumber > n) {
                break;
            }
            System.out.print("fizz, ");
            releaseSemaphore();
        }
    }

    public void buzz() throws InterruptedException {
        while (true) {
            buzzSemaphore.acquire();
            if (currentNumber > n) {
                break;
            }
            System.out.print("buzz, ");
            releaseSemaphore();
        }
    }

    public void fizzbuzz() throws InterruptedException {
        while (true) {
            fizzBuzzSemaphore.acquire();
            if (currentNumber > n) {
                break;
            }
            System.out.print("fizzbuzz, ");
            releaseSemaphore();
        }
    }

    public void number() throws InterruptedException {
        while (true) {
            numberSemaphore.acquire();
            if (currentNumber > n) {
                break;
            }
            if (currentNumber % 3 == 0 && currentNumber % 5 == 0) {
                fizzBuzzSemaphore.release();
            } else if (currentNumber % 3 == 0) {
                fizzSemaphore.release();
            } else if (currentNumber % 5 == 0) {
                buzzSemaphore.release();
            } else {
                System.out.print(currentNumber + ", ");
                releaseSemaphore();
            }
        }
    }

    private void releaseSemaphore() {
        currentNumber++;
        if (currentNumber > n) {
            fizzSemaphore.release();
            buzzSemaphore.release();
            fizzBuzzSemaphore.release();
        } else {
            numberSemaphore.release();
        }
    }

    public static void main(String[] args) {
        int n = 15;
        FizzBuzz fizzBuzz = new FizzBuzz(n);

        Thread threadA = new Thread(() -> {
            try {
                fizzBuzz.fizz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                fizzBuzz.buzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadD = new Thread(() -> {
            try {
                fizzBuzz.number();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
    }
}

