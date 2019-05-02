package convert;

import java.util.*;

public class Convert {
    public static List<Map<String, String>> getListFromClojure(List<Map<String, String>> base) {
        List<Map<String, String>> list = new LinkedList<>();
        for (Map<String, String> row : base) {
            Map<String, String> map = new HashMap<>();
            Set<String> keys = row.keySet();
            Collection<String> values = row.values();
            Iterator<String> iteratorK = keys.iterator();
            Iterator<String> iteratorV = values.iterator();
            for (int i = 0; i < keys.size(); i++) {
                String key = String.valueOf(iteratorK.next());
                map.put(key.replace(":", ""), iteratorV.next());
            }
            list.add(map);
        }
        return list;
    }
}
