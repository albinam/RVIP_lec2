package rvip_lec;

import java.util.LinkedList;

public class Processing {

	private Values value;
	private static LinkedList<Values> list = new LinkedList<Values>();
	private static LinkedList<Values> queue = new LinkedList<Values>();
	private static int size;

	// Создает и добавляет новое значение в связанный список
	public void addLink() {
		value = new Values();
		list.add(value);
	}

	// Устанавливает поле запроса в значениях в статус истина
	public void sendRequest(int index) {
		value = list.get(index);
		value.setRequest(true);
	}

	//Проверяет, был ли отправлен запрос
	public boolean checkRequest() {
		size = list.size();
		boolean hasRequest = false;

		for (int i = 0; i < size; i++) {
			value = list.get(i);
			if (value.getRequest()) {
				hasRequest = true;
			}
		}
		return hasRequest;
	}

	// Посылает ответ устанавливая reply = true для индекса, который вызван
	public void sendReply(int index) {
		value = list.get(index);
		value.setReply(true);
	}

	// Возвращает true если все ответы истины
	public boolean checkReplys() {
		boolean allTrue = true;
		size = list.size();
		for (int i = 0; i < size; i++) {
			value = list.get(i);
			if (!value.getReply()) {
				allTrue = false;
			}
		}
		return allTrue;
	}

	//Добавляет занчение в конец очереди
	public void enque(int index) {
		value = list.get(index);
		queue.addLast(value);
	}

	//Удаляет первый элемент из очереди
	public void deque() {
		if (!queue.isEmpty()) {
			queue.removeFirst();
		}
	}

	// Проверяет, является ли объект значений, соответствующий переданному индексу, первым в очереди
	public boolean checkFirst(int index) {
		boolean reply = false;
		value = list.get(index);
		if (value == queue.getFirst()) {
			reply = true;
		}
		return reply;
	}

	// Возвращает размер списка
	public void Print() {
		System.out.println(list.size());
	}

}
