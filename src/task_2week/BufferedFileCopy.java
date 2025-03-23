package task_2week;
/*
제로베이스 35기 서요한
*/
import java.io.*;

public class BufferedFileCopy {
    private static final int BUFFER_SIZE = 8192;

    public static void main(String[] args) {
        File source = new File("test.txt");
        File target = new File("test_copy.txt");

        try {
            copyFile(source, target);
            System.out.println("파일 복사가 완료되었습니다.");
        } catch (InvalidPathException e) {
            System.out.println("잘못된 파일 경로입니다. " + e.getMessage());

        } catch (IOException e) {
            System.out.println("파일 복사 중 오류가 발생했습니다.");
        }
    }

    private static void copyFile(File source, File target) throws IOException {
        validatePaths(source, target);

        try(BufferedInputStream bis =
                    new BufferedInputStream(new FileInputStream(source));
            BufferedOutputStream bos =
                    new BufferedOutputStream(new FileOutputStream(target));) {

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;

            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0,bytesRead);
            }
            bos.flush();
        }

    }

    private static void validatePaths(File source, File target) throws InvalidPathException {
        if (!source.exists()) {
            throw new InvalidPathException(source.getPath(), "원본 파일이 존재하지 않습니다.");
        }
        if (!source.canRead()) {
            throw new InvalidPathException(source.getPath(), "원본 파일을 읽을 수 없습니다.");
        }
        if (target.exists() && !target.canWrite()) {
            throw new InvalidPathException(source.getPath(), "대상 파일에 쓸 수 없습니다.");
        }
    }

    private static class InvalidPathException extends IOException{

        public InvalidPathException(String path, String reason) {
            super(String.format("%s: %s", reason, path));
        }
    }

}
