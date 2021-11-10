package rvip_lec;

import java.util.NoSuchElementException;

class Threading implements Runnable {
	private Thread t;
	private String threadName;
	private static int count = 0;
	private int num;
	private int runTime;
	private Processing test = new Processing();

	//Конструктор определяет, как долго будет работать процесс
	Threading(String name) {
		threadName = name;
		System.out.println("Создается " + threadName);
		count++;
		num = count - 1;
		runTime = count * 10;
		test.addLink();
		test.Print();
	}

	//
	public void run() {
		System.out.println("Выполняется " + threadName);
		try {
			for (int i = runTime; i > 0; i--) {
				System.out.println("Поток: " + threadName + ", " + i);
				Thread.sleep(50);
				//Проверка на запросы
				if (test.checkRequest()) {
					//Отправляет ответ в текущем потоке
					test.sendReply(num);
				}
			}
		} catch (Exception e) {
			System.out.println("Поток: " + threadName + " прерван");
		}
		//Отправляет запрос после завершения задачи
		test.sendRequest(num);
		//Добавляет текущий поток в конец очереди
		test.enque(num);
		// Ожидает получения всех ответов, а также проверяет наличие новых запросов
		while (!test.checkReplys()) {
			if (test.checkRequest()) {
				test.sendReply(num);
			}
		}
		//Ожидание, пока этот поток не станет первым потоком в очереди
		try {
			while (!test.checkFirst(num)) {
				System.out.println("Ожидание");
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (NoSuchElementException e) {
			test.enque(num);
		}
		//Вход в критическую область
		enterCS();
		//Выход из очереди
		try {
			test.deque();
		} catch (NoSuchElementException e) {
			System.out.println("Очередь пуста");
		}
		test.sendReply(num);
		System.out.println("Поток: " + threadName + " вышел из критической области");
	}

	// Вызывает функцию запуска
	public void start() {
		System.out.println("Запуск потока: " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}

	//Попадание в критическую область
	public void enterCS() {
		try {
			System.out.println("Поток: " + threadName + " попадает в критическую область");
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
