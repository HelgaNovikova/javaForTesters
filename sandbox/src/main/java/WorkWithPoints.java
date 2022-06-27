public class WorkWithPoints {

    public static double distance(Point p1, Point p2){
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    public static void main(String[] args) {
        Point point1 = new Point(1, 2);
        Point point2 = new Point(6, -9);
        System.out.println("Distance calculated by class method: " + point1.distance(point2));
        System.out.println("Distance calculated by function:" + distance(point1, point2));
    }
}
