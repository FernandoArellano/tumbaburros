package moduleui;



import moduleservice.process.ShopService;

import java.util.List;

public class GuiService {

    private ShopService shopService;

    public GuiService(ShopService shopService) {
        this.shopService = shopService;
    }

    public List<String> getLatestItems(){
        return this.shopService.getAllShopItems();
    }
}
