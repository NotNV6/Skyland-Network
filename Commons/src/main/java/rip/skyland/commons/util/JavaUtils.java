package rip.skyland.commons.util;

import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@UtilityClass
public class JavaUtils {

    public <K, V> Map<K, V> mapOf(K k1, V v1) {
        return new HashMap<K, V>() {{
            put(k1, v1);
        }};
    }

    public <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2) {
        return mapOf(Arrays.asList(k1, k2), Arrays.asList(v1, v2));
    }

    public <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3) {
        return mapOf(Arrays.asList(k1, k2, k3), Arrays.asList(v1, v2, v3));
    }

    public <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        return mapOf(Arrays.asList(k1, k2, k3, k4), Arrays.asList(v1, v2, v3, v4));
    }

    public <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return mapOf(Arrays.asList(k1, k2, k3, k4, k5), Arrays.asList(v1, v2, v3, v4, v5));
    }

    public <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
        return mapOf(Arrays.asList(k1, k2, k3, k4, k5, k6), Arrays.asList(v1, v2, v3, v4, v5, v6));
    }

    public <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7) {
        return mapOf(Arrays.asList(k1, k2, k3, k4, k5, k6, k7), Arrays.asList(v1, v2, v3, v4, v5, v6, v7));
    }

    public <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8) {
        return mapOf(Arrays.asList(k1, k2, k3, k4, k5, k6, k7, k8), Arrays.asList(v1, v2, v3, v4, v5, v6, v7, v8));
    }

    public <K, V> Map<K, V> mapOf(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9) {
        return mapOf(Arrays.asList(k1, k2, k3, k4, k5, k6, k7, k8, k9), Arrays.asList(v1, v2, v3, v4, v5, v6, v7, v8, v9));
    }

    public <K, V> Map<K, V> mapOf(List<K> keys, List<V> values) {
        if (keys.size() != values.size()) {
            throw new IndexOutOfBoundsException("amount of keys and values is not equal");
        }

        return new HashMap<K, V>() {{
            IntStream.range(0, keys.size()).forEach(index -> put(keys.get(index), values.get(index)));
        }};
    }

    /**
     * Reverses an array
     *
     * @param array the array to be reversed
     * @param <T> the type to be returned
     * @return the reversed array
     */
    @SuppressWarnings("unchecked")
    public <T> T[] reverseArray(T[] array) {
        List<T> list = Arrays.asList(array);
        Collections.reverse(list);

        return list.toArray(array);
    }

    /**
     * Translates a string
     *
     * @param string the string to be translated
     * @param translations the map of translations
     * @return the translated string
     */
    public String translate(String string, Map<String, String> translations) {
        for (Map.Entry<String, String> entry : translations.entrySet()) {
            string = string.replace(entry.getKey(), entry.getValue());
        }

        return string;
    }

    /**
     * Translates a list
     *
     * @param list the list to be translate
     * @param translations the map of translatiosn
     * @return the translation list
     */
    public List<String> translate(List<String> list, Map<String, String> translations) {
        return list.stream()
                .map(string -> translate(string, translations))
                .collect(Collectors.toList());
    }
}