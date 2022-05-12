import java.util.HashMap;
import java.util.Map;

@Singleton
public class Facade  {

    private Map<Integer, User> users = new HashMap<Integer, User>();

    public Facade(){

    }
}