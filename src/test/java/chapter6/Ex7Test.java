package chapter6;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.*;

public class Ex7Test {

    @Test
    public void testKeyWithMaxValue() throws Exception {
        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
        map.put("smallest", 5L);
        map.put("smaller", 9L);
        map.put("medium", 30L);
        map.put("larger", 300L);
        map.put("largest", 4327894723897L);

        String result = Ex7.keyWithMaxValue(map);

        Assertions.assertThat(result).isEqualTo("largest");
    }
}