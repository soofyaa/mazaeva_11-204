import java.io.*;

public class FileCopier implements Runnable {
    private String sourceFilePath;
    private String destinationFilePath;

    public FileCopier(String sourceFilePath, String destinationFilePath) {
        this.sourceFilePath = sourceFilePath;
        this.destinationFilePath = destinationFilePath;
    }

    public void run() {
        try (InputStream in = new BufferedInputStream(new FileInputStream(sourceFilePath));
             OutputStream out = new BufferedOutputStream(new FileOutputStream(destinationFilePath))) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String sourceFilePath = "/path/to/source/file.txt";
        String destinationDirectoryPath = "/path/to/destination/directory";
        int numberOfCopies = 5;

        File sourceFile = new File(sourceFilePath);
        File destinationDirectory = new File(destinationDirectoryPath);

        if (!sourceFile.exists() || !sourceFile.isFile()) {
            throw new IllegalArgumentException("Source file does not exist or is not a file");
        }

        if (!destinationDirectory.exists() || !destinationDirectory.isDirectory()) {
            throw new IllegalArgumentException("Destination directory does not exist or is not a directory");
        }

        for (int i = 0; i < numberOfCopies; i++) {
            String destinationFilePath = destinationDirectoryPath + "/copy" + i + ".bin";
            FileCopier fileCopier = new FileCopier(sourceFilePath, destinationFilePath);
            Thread thread = new Thread(fileCopier);
            thread.start();
        }
    }
}