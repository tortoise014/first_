package check;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * @Author tortoise
 * @Date 2025/3/8 2:23
 * @PackageName:check
 * @ClassName: Main
 * @Description:
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("请提供三个参数：原文文件路径、抄袭版文件路径、答案文件路径");
            return;
        }

        try {
            // 1. 从命令行参数获取文件路径
            String originalFilePath = args[0]; // 原文文件路径
            String plagiarizedFilePath = args[1]; // 抄袭版文件路径
            String answerFilePath = args[2]; // 答案文件路径
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
