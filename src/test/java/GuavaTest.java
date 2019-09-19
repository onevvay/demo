import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;

import java.util.List;

/**
 * @author oneway
 * @date 2019/09/18
 */
public class GuavaTest {
    // 连接器
    private static final Joiner joiner = Joiner.on(",").skipNulls();
    // 分割器
    private static final Splitter splitter = Splitter.on(",").trimResults().omitEmptyStrings();

    // 字符串匹配器
    private static final CharMatcher CHAR_MATCHER_DIGIT = CharMatcher.digit();
    private static final CharMatcher CHAR_MATCHER_ANY = CharMatcher.any();

    public static void main(String[] args) {
        // 一对多的Map
        Multimap<String, String> map = ArrayListMultimap.create();
        map.put("1", "abc");
        map.put("1", "qqq");
        List<String> collection = (List<String>) map.get("1");
        System.out.println(collection);

        // 连接数组为字符串
        String join = joiner.join(Lists.newArrayList("a", null, "b"));
        System.out.println("join = " + join);

        // 分割字符串为数组
        for (String s : splitter.split(" a,   ,b,,,,,,   c,")) {
            System.out.println("|" + s + "|");
        }

        // 只保留匹配的字符，其他移除
        System.out.println(CHAR_MATCHER_DIGIT.retainFrom("asdfd21gg456"));
        // 移除匹配的字符
        System.out.println(CHAR_MATCHER_DIGIT.removeFrom("twedd1213ii001"));

        System.out.println(CHAR_MATCHER_ANY.inRange('a', 'f').or(CHAR_MATCHER_ANY.is('j')).replaceFrom("abcdefghijk", "*"));

        // 快速完成到集合的转换
        List<Integer> integers = Ints.asList(1, 3, 5, 7, 9);
        System.out.println(integers);
        System.out.println(Ints.join(",", 2, 4, 6, 8, 10));

        // 原生类型数组快速合并
        int[] concat = Ints.concat(new int[]{1, 2}, new int[]{2, 3, 4});
        System.out.println(concat.length);

        // 最大、最小
        System.out.println(Ints.max(concat) + ", " + Ints.min(concat));

        // 是否包含
        System.out.println(Ints.contains(concat, 6));


        // 无序可重复的Set
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("d");
        System.out.println(multiset.size());
        System.out.println(multiset.count("a"));
    }

}

