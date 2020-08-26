public class Cities {

    public String cityName;
    public int xCoordinate;
    public int yCoordinate;
    public String neighborName;
    public int neighborXCoordinate;
    public int neighborYCoordinate;

    public Cities(String cityName, int xCoordinate, int yCoordinate){
        this.cityName = cityName;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public Cities(String cityName, int xCoordinate, int yCoordinate,
                  String neighborName){
        this(cityName, xCoordinate, yCoordinate);
        this.neighborName= neighborName;
    }

    public Cities(String cityName, int xCoordinate, int yCoordinate,
                  String neighborName, int neighborXCoordinate, int neighborYCoordinate){
        this(cityName, xCoordinate, yCoordinate, neighborName);
        this.neighborXCoordinate = neighborXCoordinate;
        this.neighborYCoordinate = neighborYCoordinate;
    }

    public Cities(String cityName, String neighborName){
        this.cityName = cityName;
        this.neighborName = neighborName;
    }

}
