package modulerepository;

import java.util.List;

public class MySqlManager {

    public List<String> getShopItems(){
        //connects to database
        return List.of("Apple", "Snickers", "Banana");
    }
}
