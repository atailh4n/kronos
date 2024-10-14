package parser;

import java.nio.file.*;
import java.io.IOException;

public class KronosFileLoader {
    public static String loadFile(String path) throws IOException {
        return Files.readString(Path.of(path));
    }
}
