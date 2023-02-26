package exercise;

// BEGIN
interface Home  {
    double getArea(); //  предназначен для получения общей площади объекта недвижимости.

    default int compareTo(Home another) {
        if (getArea() == another.getArea()) {
            return 0;
        }
        return (getArea() < another.getArea()) ? -1 : 1;
    };// — Служит для сравнения двух объектов недвижимости по их площади.

}
// END
