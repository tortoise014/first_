package check;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author tortoise
 * @Date 2025/3/8 0:10
 * @PackageName:firstHomework
 * @ClassName: SimpleFileReader
 * @Description:读取文件内容使用绝对路径
 * @Version 1.0
 */
public class SimpleFileReader implements FileReader{

    @Override
    public String read(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (InputStream inputStream = new FileInputStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString().trim(); // 去除末尾的换行符
    }
}
