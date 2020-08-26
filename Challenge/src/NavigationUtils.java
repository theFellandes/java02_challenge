import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NavigationUtils {

    public static void canvasCreator () {

        StdDraw.enableDoubleBuffering ();
        int canvasWidth = 1800;
        int canvasHeight = 900;
        StdDraw.setCanvasSize (canvasWidth, canvasHeight);
        StdDraw.setXscale (0.0, canvasWidth);
        StdDraw.setYscale (0.0, canvasHeight);

    }

    public static ArrayList<Boundaries> boundaryReader(String boundaryTextName)
            throws FileNotFoundException {

        File boundaryFile = new File(boundaryTextName);
        Scanner boundaryInput = new Scanner(boundaryFile);
        ArrayList<Boundaries> boundaries = new ArrayList<>();

        while (boundaryInput.hasNext()) {
            String coordinateString = boundaryInput.nextLine();
            String xCoordString = coordinateString.substring(0, coordinateString.indexOf(","));
            String yCoordString = coordinateString.substring(coordinateString.indexOf(",") + 1);
            int xCoord = Integer.parseInt(xCoordString);
            int yCoord = Integer.parseInt(yCoordString);
            boundaries.add(new Boundaries(xCoord,yCoord));

        }
        return boundaries;

    }

    public static ArrayList<Cities> cityReader() throws FileNotFoundException{
        File cityFile = new File("cities.txt");
        Scanner cityInput = new Scanner(cityFile);
        ArrayList<Cities> cities = new ArrayList<>();

        while(cityInput.hasNext()){
            String cityString = cityInput.nextLine();
            String cityName = cityString.substring(0, cityString.indexOf(","));
            String xCoordinateString = cityString.substring(cityString.indexOf(",")+1,
                    cityString.indexOf(",", cityString.indexOf(",") + 2));
            int startingPointY = cityName.length() + xCoordinateString.length() + 2;
            String yCoordinateString = cityString.substring(startingPointY, cityString.indexOf(",", startingPointY));
            String fooString = cityString.substring(cityString.lastIndexOf(","));
            int xCoord = Integer.parseInt(xCoordinateString);
            int yCoord = Integer.parseInt(yCoordinateString);
            cities.add(new Cities(cityName, xCoord, yCoord));
        }

        return cities;

    }

    public static void plotMap() throws FileNotFoundException {

        ArrayList<Boundaries> asianBoundaries = boundaryReader("boundary1.txt");
        ArrayList<Boundaries> europeanBoundaries = boundaryReader("boundary2.txt");
        ArrayList<Cities> citiesArrayList = cityReader();
        Font font = new Font("Helvetica", Font.PLAIN, 13);

        for(int i = 0; i < asianBoundaries.size() - 1; i++){
            StdDraw.line(asianBoundaries.get(i).xCoordinate, asianBoundaries.get(i).yCoordinate,
                    asianBoundaries.get(i + 1).xCoordinate, asianBoundaries.get(i + 1).yCoordinate);
        }

        for(int i = 0; i < europeanBoundaries.size() - 1; i++){
            StdDraw.line(europeanBoundaries.get(i).xCoordinate, europeanBoundaries.get(i).yCoordinate,
                    europeanBoundaries.get(i + 1).xCoordinate, europeanBoundaries.get(i + 1).yCoordinate);
        }

        for (Cities cities : citiesArrayList) {
            StdDraw.setFont(font);
            StdDraw.text(cities.xCoordinate + 13, cities.yCoordinate - 13,
                    cities.cityName);
            StdDraw.filledCircle(cities.xCoordinate, cities.yCoordinate, 5);
        }

    }

    public static ArrayList<Cities> cityDatabase(ArrayList<Cities> cities) throws FileNotFoundException {
        File roadFile = new File("roads.txt");
        Scanner roadInput = new Scanner(roadFile);
        ArrayList<Cities> neighboringArray = new ArrayList<>();

        while(roadInput.hasNext()){
            String roadString = roadInput.nextLine();
            String cityString = roadString.substring(0,roadString.indexOf(","));
            String neighborString = roadString.substring(roadString.indexOf(",")+1);
            neighboringArray.add(new Cities(cityString, neighborString));
        }
        ArrayList<Cities> cityDatabase = new ArrayList<>();

        for (Cities city : cities) {
            for (Cities value : neighboringArray) {
                if (city.cityName.contentEquals(value.cityName)) {
                    cityDatabase.add(new Cities(city.cityName, city.xCoordinate, city.yCoordinate, value.neighborName));
                }
            }
        }
        ArrayList<Cities> cityDatabase2 = new ArrayList<>();

        for (Cities value : cityDatabase) {
            for (Cities city : cities) {
                if (value.neighborName.contentEquals(city.cityName)) {
                    cityDatabase2.add(new Cities(value.cityName, value.xCoordinate, value.yCoordinate,
                            value.neighborName, city.xCoordinate, city.yCoordinate));
                }
            }
        }

        return cityDatabase2;

    }

    public static void connectDots(ArrayList<Cities> cities){
        StdDraw.setPenColor(Color.RED);
        for(Cities cityList : cities){
            StdDraw.line(cityList.xCoordinate,cityList.yCoordinate,
                    cityList.neighborXCoordinate, cityList.neighborYCoordinate);
        }
    }


}
