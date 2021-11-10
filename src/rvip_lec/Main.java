package rvip_lec;

import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		String input;
		int number;
		boolean flag = false;
		Threading thread;
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Введите количество потоков");
		//Цикл, который заставляет ввести число
		do {
			input = scan.next();
			try {
				number = Integer.parseInt(input);
				for (int i = 1; i <= number; i++) {
					thread = new Threading("Thread-" + i);
					thread.start();
					}
			} catch (Exception e) {
				System.out.println("Введите количество потоков");
				flag = true;
			}
		} while (flag);
		scan.close();
	}
}
