package moduleservice.utility;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceUtil {

    public static List<String> filterItems(List<String> items){
        List<String> result = items.stream().filter(s->s.equals("Banana")).collect(Collectors.toList());
        return result;
    }

}
