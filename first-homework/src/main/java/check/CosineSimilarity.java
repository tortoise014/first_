package check;

import java.util.*;

/**
 * @Author tortoise
 * @Date 2025/3/8 0:52
 * @PackageName:check
 * @ClassName: CosineSimilarity
 * @Description:使用 TF-IDF + 余弦相似度 计算相似度
 * @Version 1.0
 */
public class CosineSimilarity implements SimilarityCalculator {

    /**
     * 计算余弦相似度
     *
     * @param text1 文本1的分词结果
     * @param text2 文本2的分词结果
     * @return 余弦相似度（范围：0 到 1）
     */
    public double calculate(List<String> text1, List<String> text2) {
        // 计算词频向量
        Map<String, Integer> vector1 = getTermFrequency(text1);
        Map<String, Integer> vector2 = getTermFrequency(text2);

        // 计算点积
        double dotProduct = 0.0;
        for (String key : vector1.keySet()) {
            if (vector2.containsKey(key)) {
                dotProduct += vector1.get(key) * vector2.get(key);
            }
        }

        // 计算向量模长
        double norm1 = calculateNorm(vector1);
        double norm2 = calculateNorm(vector2);

        // 计算余弦相似度
        if (norm1 == 0 || norm2 == 0) {
            return 0.0; // 避免除零错误
        }
        return dotProduct / (norm1 * norm2);
    }

    /**
     * 计算词频向量
     *
     * @param words 分词后的文本
     * @return 词频向量
     */
    private Map<String, Integer> getTermFrequency(List<String> words) {
        Map<String, Integer> termFrequency = new HashMap<>();
        for (String word : words) {
            termFrequency.put(word, termFrequency.getOrDefault(word, 0) + 1);
        }
        return termFrequency;
    }

    /**
     * 计算向量模长
     *
     * @param vector 词频向量
     * @return 向量模长
     */
    private double calculateNorm(Map<String, Integer> vector) {
        double sum = 0.0;
        for (int value : vector.values()) {
            sum += Math.pow(value, 2);
        }
        return Math.sqrt(sum);
    }
}
