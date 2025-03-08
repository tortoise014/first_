package check;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author tortoise
 * @Date 2025/3/8 0:59
 * @PackageName:PACKAGE_NAME
 * @ClassName: check.test
 * @Description:
 * @Version 1.0
 */
public class test {

    @Test
    void testReadFile() throws IOException {
        // 1. 准备测试文件路径
        String filePath = "D://homework//test_text//orig.txt";

        // 2. 创建 SimpleFileReader 实例
        SimpleFileReader fileReader = new SimpleFileReader();

        // 3. 读取文件内容
        String content = null;
        try {
            content = fileReader.read(filePath);
        } catch (IOException e) {
            fail("文件读取失败: " + e.getMessage());
        }

        // 4. 验证读取的内容是否正确
        assertNotNull(content, "文件内容不应为空");
        System.out.println("文件内容: " + content);

    }
    @Test
    void testPreprocessWithPartOfSpeechFilter() {
        // 1. 创建 AdvancedTextPreprocessor 实例
        SimpleTextPreprocessor preprocessor = new SimpleTextPreprocessor();

        // 2. 输入文本
        String text = "今天是星期天，天气晴，今天晚上我要去看电影。";

        // 3. 调用预处理方法
        List<String> result = preprocessor.preprocess(text);

        System.out.println(result);
    }
    @Test
    void testCalculateSimilarityWithDifferentTexts() {
        // 1. 初始化 CosineSimilarity
        CosineSimilarity similarityCalculator = new CosineSimilarity();

        // 2. 准备测试数据
        List<String> text1 = Arrays.asList("今天", "天气", "晴朗", "我", "去", "看电影");
        List<String> text2 = Arrays.asList("明天", "天气", "可能", "会", "下雨");

        // 3. 计算相似度
        double similarity = similarityCalculator.calculate(text1, text2);

        // 4. 验证结果
       // assertTrue(similarity < 0.5, "相似度应小于 0.5");
        System.out.println("相似度: " + similarity);
    }
    @Test
    void testCalculateSimilarity() {
        // 1. 初始化 TFIDFCosineSimilarity
        CosineSimilarity similarityCalculator = new CosineSimilarity();

        // 2. 准备测试数据
        List<String> text1 = Arrays.asList("今天", "天气", "晴朗", "我", "去", "看电影");
        List<String> text2 = Arrays.asList("今天", "天气", "很好", "我", "去", "看电影");

        // 3. 计算相似度
        double similarity = similarityCalculator.calculate(text1, text2);

        // 4. 验证结果
        assertTrue(similarity > 0.8, "相似度应大于 0.8");
        System.out.println("相似度: " + similarity);
    }


    @Test
    void testCalculateWithDifferentDocuments() {
        String filePath = "D://homework//test_text//orig.txt";
        String filePath2 = "D://homework//test_text//orig_0.8_add.txt";

        // 2. 创建 SimpleFileReader 实例
        SimpleFileReader fileReader = new SimpleFileReader();

        // 3. 读取文件内容
        String content1 = null;
        try {
            content1 = fileReader.read(filePath);
        } catch (IOException e) {
            fail("文件读取失败: " + e.getMessage());
        }
        String content2 = null;
        try {
            content2 = fileReader.read(filePath2);
        } catch (IOException e) {
            fail("文件读取失败: " + e.getMessage());
        }
        // 1. 创建 AdvancedTextPreprocessor 实例
        SimpleTextPreprocessor preprocessor = new SimpleTextPreprocessor();
        List<String> result1 = preprocessor.preprocess(content1);
        List<String> result2 = preprocessor.preprocess(content2);
       // CosineSimilarity tfidfCosineSimilarity = new CosineSimilarity(documents);
       // double similarity = tfidfCosineSimilarity.calculate(result1, result2);
    }
    @Test
    void testWriteToFile() throws IOException {
        try {
            // 1. 从命令行参数获取文件路径
            String originalFilePath = "D://homework//test_text//orig.txt";; // 原文文件路径
            String plagiarizedFilePath = "D://homework//test_text//orig_0.8_add.txt"; // 抄袭版文件路径
            String answerFilePath = "D://homework//test_text//answer.txt"; // 答案文件路径
            // 2. 创建 SimpleFileReader 实例
            SimpleFileReader fileReader = new SimpleFileReader();

            // 3. 读取文件内容
            String originalText = null;
            String plagiarizedText = null;
            try {
                originalText = fileReader.read(originalFilePath);
            } catch (IOException e) {
                fail("文件读取失败: " + e.getMessage());
            }
            try {
                plagiarizedText = fileReader.read(plagiarizedFilePath);
            } catch (IOException e) {
                fail("文件读取失败: " + e.getMessage());
            }

            // 3. 对文本进行预处理
            SimpleTextPreprocessor preprocessor = new SimpleTextPreprocessor();
            List<String> originalWords = preprocessor.preprocess(originalText);
            List<String> plagiarizedWords = preprocessor.preprocess(plagiarizedText);

            // 1. 初始化 CosineSimilarity
            CosineSimilarity similarityCalculator = new CosineSimilarity();
            // 4. 计算相似度
            double similarity = similarityCalculator.calculate(originalWords, plagiarizedWords);


            // 5. 将结果写入答案文件，并标注文件名
            String result = String.format("原文文件: %s\n抄袭版文件: %s\n相似度: %.2f",
                    originalFilePath, plagiarizedFilePath, similarity);
            Files.write(Paths.get(answerFilePath), result.getBytes());

            System.out.println("相似度已写入文件: " + answerFilePath);
        } catch (IOException e) {
            System.err.println("文件读写失败: " + e.getMessage());
        }
    }
}


