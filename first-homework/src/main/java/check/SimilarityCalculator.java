package check;

import java.util.List;

/**
 * @Author tortoise
 * @Date 2025/3/8 0:08
 * @PackageName:firstHomework
 * @ClassName: SimilarityCalculator
 * @Description:负责计算两个文本的相似度
 * @Version 1.0
 */
public interface SimilarityCalculator {
    double calculate(List<String> text1, List<String> text2);
}
