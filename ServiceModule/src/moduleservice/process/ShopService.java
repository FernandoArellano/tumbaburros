package moduleservice.process;


import modulerepository.MySqlManager;
import moduleservice.utility.ServiceUtil;

import java.util.List;

public class ShopService {

    private MySqlManager mySqlManager;

    public ShopService(MySqlManager mySqlManager) {
        this.mySqlManager = mySqlManager;
    }

    public List<String> getAllShopItems() {

        List<String> items = this.mySqlManager.getShopItems();

        return ServiceUtil.filterItems(items);
    }
}
