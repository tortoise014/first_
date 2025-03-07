package check;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author tortoise
 * @Date 2025/3/8 0:08
 * @PackageName:firstHomework
 * @ClassName: FileReader
 * @Description:负责读取文件内容
 * @Version 1.0
 */

public interface FileReader {
    String read(String filePath) throws IOException;

}
