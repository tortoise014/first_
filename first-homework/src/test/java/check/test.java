package check;

import check.SimpleFileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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

}

