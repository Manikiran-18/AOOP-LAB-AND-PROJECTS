import java.io.*;
import java.nio.file.Files;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileCompressorTest {

    // Service utility for file compression and decompression
    public static class FileCompressor {

        // Compress a file
        public static void compressFile(File inputFile, File outputFile) throws IOException {
            try (FileInputStream fis = new FileInputStream(inputFile);
                 FileOutputStream fos = new FileOutputStream(outputFile);
                 GZIPOutputStream gzos = new GZIPOutputStream(fos)) {

                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    gzos.write(buffer, 0, length);
                }
            }
        }

        // Decompress a file
        public static void decompressFile(File inputFile, File outputFile) throws IOException {
            try (FileInputStream fis = new FileInputStream(inputFile);
                 GZIPInputStream gzis = new GZIPInputStream(fis);
                 FileOutputStream fos = new FileOutputStream(outputFile)) {

                byte[] buffer = new byte[1024];
                int length;
                while ((length = gzis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
            }
        }
    }

    // Test class for FileCompressor
    public static class FileCompressorTest {

        private File testFile;
        private File compressedFile;
        private File decompressedFile;

        @BeforeEach
        public void setUp() throws IOException {
            testFile = File.createTempFile("testFile", ".txt");
            compressedFile = File.createTempFile("compressedFile", ".gz");
            decompressedFile = File.createTempFile("decompressedFile", ".txt");

            // Write some content to the test file
            Files.write(testFile.toPath(), "This is a test file.".getBytes());
        }

        @AfterEach
        public void tearDown() {
            testFile.delete();
            compressedFile.delete();
            decompressedFile.delete();
        }

        @Test
        public void testCompressSmallFile() throws IOException {
            FileCompressor.compressFile(testFile, compressedFile);
            assertTrue(compressedFile.exists(), "Compressed file should exist.");
        }

        @Test
        public void testCompressLargeFile() throws IOException {
            // Create a large file by copying content multiple times
            File largeFile = new File("largeFile.txt");
            try (FileOutputStream fos = new FileOutputStream(largeFile)) {
                for (int i = 0; i < 1000; i++) {
                    fos.write("This is a large test file.".getBytes());
                }
            }

            File largeCompressedFile = new File("largeCompressedFile.gz");
            FileCompressor.compressFile(largeFile, largeCompressedFile);
            assertTrue(largeCompressedFile.exists(), "Compressed large file should exist.");

            // Clean up
            largeFile.delete();
            largeCompressedFile.delete();
        }

        @Test
        public void testDecompressFile() throws IOException {
            FileCompressor.compressFile(testFile, compressedFile);
            FileCompressor.decompressFile(compressedFile, decompressedFile);

            assertTrue(decompressedFile.exists(), "Decompressed file should exist.");
            assertArrayEquals(Files.readAllBytes(testFile.toPath()), Files.readAllBytes(decompressedFile.toPath()), "Decompressed content should match original content.");
        }
    }

    // Entry point for running the FileCompressor utility
    public static void main(String[] args) {
        try {
            File testFile = new File("example.txt");
            File compressedFile = new File("example.gz");
            File decompressedFile = new File("example_decompressed.txt");

            // Compress and decompress example
            FileCompressor.compressFile(testFile, compressedFile);
            FileCompressor.decompressFile(compressedFile, decompressedFile);

            System.out.println("File compression and decompression completed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
