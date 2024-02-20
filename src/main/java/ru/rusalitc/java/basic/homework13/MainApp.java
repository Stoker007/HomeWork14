package ru.rusalitc.java.basic.homework13;

public class MainApp {
    public static void main(String[] args) throws InterruptedException {
        int lengthArray = 100000000;
        double[] array = new double[lengthArray];
        long time = System.currentTimeMillis();
        for (int i = 0; i < lengthArray; i++) {
            array[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
        }
        System.out.println("Затрачено времени на заполнение массива из " + lengthArray +" элементов без разделение на потоки, м/с: "+ (System.currentTimeMillis() - time));

        time = System.currentTimeMillis();
        double[] array2 = new double[lengthArray];
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < lengthArray / 4; i++) {
                array2[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = lengthArray / 4; i < lengthArray / 2; i++) {
                array2[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            }
        });
        Thread t3 = new Thread(() -> {
            for (int i = lengthArray / 2; i < 3 * lengthArray / 4; i++) {
                array2[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            }
        });
        Thread t4 = new Thread(() -> {
            for (int i = 3 * lengthArray / 4; i < lengthArray; i++) {
                array2[i] = 1.14 * Math.cos(i) * Math.sin(i * 0.2) * Math.cos(i / 1.2);
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        System.out.println("Затрачено времени на заполнение массива из " + lengthArray + " элементов с разделением на 4 потока, м/с: " + (System.currentTimeMillis() - time));

    }

}
