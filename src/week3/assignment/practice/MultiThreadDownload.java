package week3.assignment.practice;

public class MultiThreadDownload {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new FileDownload("파일_1.zip"));
        Thread thread2 = new Thread(new FileDownload("파일_2.mp4"));
        Thread thread3 = new Thread(new FileDownload("파일_3.pdf"));

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private static class FileDownload implements Runnable {

        private String fileName;

        public FileDownload(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            try {
                System.out.println(fileName + " 다운로드 시작...");
                Thread.sleep(10000);

            } catch (InterruptedException e) {
                System.out.println(fileName + "다운로드 중 오류 발생");

            }finally {
                System.out.println(fileName + " 다운로드 완료!");
            }
        }

        public String getFileName() {
            return fileName;
        }
    }
}
