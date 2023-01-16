package loyality_platform_model.DBController;

//Todo finire.
public class DBMSController {

    private static DBMSController instance;

    private final String nameDb;

    public DBMSController(String nameDb){
        this.nameDb = nameDb;
    }

    public static DBMSController getInstance() {
        if(instance == null){
            instance = new DBMSController(getInstance().nameDb);
        }
        return instance;
    }
}
