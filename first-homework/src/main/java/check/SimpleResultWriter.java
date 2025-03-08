package check;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author tortoise
 * @Date 2025/3/8 2:21
 * @PackageName:check
 * @ClassName: SimpleResultWriter
 * @Description:
 * @Version 1.0
 */
public class SimpleResultWriter implements ResultWriter {
    @Override
    public void write(String filePath, double similarity) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.format("%.2f", similarity));
        }
    }
}
