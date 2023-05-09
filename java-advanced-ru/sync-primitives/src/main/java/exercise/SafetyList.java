package exercise;

class SafetyList {
    int[] array = new int[5000];
    int counter = 0;

    // BEGIN
    public int getSize() {
        return counter;
    }

    public synchronized void add(int num) {
        array[counter] = num;
        counter++;
    }

    public int get(int index) {
        return array[index];
    }
    // END
}
