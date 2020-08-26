import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Navigation {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Cities> cities = NavigationUtils.cityReader();
        ArrayList<Cities> cityDatabase = NavigationUtils.cityDatabase(cities);
        NavigationUtils.canvasCreator();
        NavigationUtils.plotMap();
        NavigationUtils.connectDots(cityDatabase);
        StdDraw.show();

    }

}
