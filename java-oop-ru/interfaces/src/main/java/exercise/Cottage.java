package exercise;

// BEGIN
class Cottage implements Home{
    double area;
    int floorCount;;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public String toString() {
        return String.format("%d этажный коттедж площадью %.1f метров", floorCount, getArea());
    }
}
// END
