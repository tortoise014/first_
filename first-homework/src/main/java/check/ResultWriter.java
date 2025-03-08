package check;

import java.io.IOException;

/**
 * @Author tortoise
 * @Date 2025/3/8 2:20
 * @PackageName:check
 * @ClassName: ResultWriter
 * @Description:
 * @Version 1.0
 */
public interface ResultWriter {
    void write(String filePath, double similarity) throws IOException;
}
