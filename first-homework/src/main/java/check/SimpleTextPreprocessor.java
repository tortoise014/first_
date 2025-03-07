package check;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Author tortoise
 * @Date 2025/3/8 0:51
 * @PackageName:check
 * @ClassName: SimpleTextPreprocessor
 * @Description:
 * @Version 1.0
 */
public class SimpleTextPreprocessor implements TextPreprocessor{
    // 停用词列表（可以根据需要扩展）
// 停用词列表
    private static final List<String> STOP_WORDS = Arrays.asList(
            "的", "是", "在", "了", "和", "有", "我", "他", "她", "它", "我们", "你们", "他们", "这", "那"
    );

    // 标点符号正则表达式
    private static final Pattern PUNCTUATION_PATTERN = Pattern.compile("[\\pP\\p{Punct}]");

    // 特殊字符正则表达式
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[@#$%^&*()_+={}\\[\\]|;:'\"<>,.?/~`]");



    @Override
    public List<String> preprocess(String text) {
        // 1. 转换为小写
        text = text.toLowerCase();


        // 3. 去除特殊字符
        text = SPECIAL_CHAR_PATTERN.matcher(text).replaceAll("");

        // 4. 去除标点符号
        text = PUNCTUATION_PATTERN.matcher(text).replaceAll("");

        // 5. 统一全角和半角字符
        text = fullWidthToHalfWidth(text);

        // 6. 去除多余空格和换行符
        text = text.replaceAll("\\s+", " ").trim();

        // 7. 使用 HanLP 分词
        List<Term> termList = HanLP.segment(text);
        List<String> words = new ArrayList<>();
        for (Term term : termList) {
            words.add(term.word);
        }

        // 8. 去除停用词
        words = words.stream()
                .filter(word -> !STOP_WORDS.contains(word))
                .collect(Collectors.toList());

        // 9. 词性过滤（只保留名词和动词）
        words = words.stream()
                .filter(word -> {
                    String pos = getPartOfSpeech(word);
                    return pos.startsWith("n") || pos.startsWith("v"); // 名词或动词
                })
                .collect(Collectors.toList());

        return words;
    }

    // 将全角字符转换为半角字符
    private String fullWidthToHalfWidth(String text) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '\u3000') {
                chars[i] = ' '; // 全角空格
            } else if (chars[i] > '\uFF00' && chars[i] < '\uFF5F') {
                chars[i] = (char) (chars[i] - 65248); // 全角字符转半角
            }
        }
        return new String(chars);
    }

    // 获取词性（简单实现，实际可以使用 HanLP 的词性标注）
    private String getPartOfSpeech(String word) {
        // 使用 HanLP 对单个词语进行分词和词性标注
        List<Term> termList = HanLP.segment(word);
        if (termList.isEmpty()) {
            return "unknown"; // 如果没有分词结果，返回未知词性
        }
        // 返回第一个词语的词性
        return termList.get(0).nature.toString();
    }
}
