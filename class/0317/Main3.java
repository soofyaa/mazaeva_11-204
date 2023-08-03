import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main3 {
    public static void main(String[] args) {
        String filePath = "path/to/file";
        int n = 10;

        File file = new File(filePath);
        byte[] buffer = new byte[(int) file.length()];

        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 1; i <= n; i++) {
            String newFilePath = "path/to/new/file" + i;
            Thread thread = new Thread(new FileCopierRunnable(buffer, newFilePath));
            thread.start();
        }
    }

    static class FileCopierRunnable implements Runnable {
        private byte[] buffer;
        private String filePath;

        public FileCopierRunnable(byte[] buffer, String filePath) {
            this.buffer = buffer;
            this.filePath = filePath;
        }

        @Override
        public void run() {
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}