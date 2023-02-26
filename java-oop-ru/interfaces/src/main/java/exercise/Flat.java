package exercise;

// BEGIN
class Flat implements Home {
    double area; // жилая площадь квартиры, число типа
    double balconyArea; // площадь балкона, число типа
    int floor; // этаж, на котором расположена квартира

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return area + balconyArea;
    }
    @Override
    public String toString() {
        return String.format("Квартира площадью %.1f метров на %d этаже", getArea(), floor);
    }
}
// END
