package exercise;

class App {

    public static void main(String[] args) throws InterruptedException {
// Создаём лист
        SafetyList list = new SafetyList();

// Создаём поток, передав туда созданный лист
        Thread thread = new Thread(new ListThread(list));
        Thread thread1 = new Thread(new ListThread(list));
// Запускаем поток
        thread.start();
        thread1.start();

// Работает примерно 1 секунду (1000 элементов * 1 мс)

// Дожидаемся его окончания
        thread.join();
        thread1.join();

// Поток добавил в лист 1000 элементов
        System.out.println(list.getSize()); // 1000
    }
}

