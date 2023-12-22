package ENUMs.Allergies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Allergies {
    private int score;
    private static Map<Integer, Allergen> map = new HashMap<>();

    static {
        for(Allergen a: Allergen.values()) {
            map.put(a.getScore(), a);
        }
    }

    Allergies(int score) {
        if(score >= 257 ) {
            score = score % 256;
        }
        this.score = score;
    }

    boolean isAllergicTo(Allergen allergen) {
        List<Allergen> list = getList();
        return list.contains(allergen);
    }

    List<Allergen> getList() {
        int aux = score;
        int maxScore = 128;
        List<Allergen> list = new ArrayList<>();
        while(aux > 0) {
            if(aux >= maxScore) {
                list.add(0, getAllergenBy(maxScore));
                aux = aux - maxScore;
            }
            maxScore = maxScore / 2;
            if(maxScore <= 0) {
                break;
            }
        }
        return list;
    }

    private Allergen getAllergenBy(int score) {
        if(map.containsKey(score)) {
            return map.get(score);
        }
        return null;
    }
}
