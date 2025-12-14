package ComplexTask.task2;

public class AVIVideoAdapter implements VideoAdapter{
    @Override
    public String convertToMP4(String file)  {
        System.out.println("Конвертация AVI->MP4");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Конвертация завершена\n");
        return file.toLowerCase().replace(".avi",".mp4");
    }
}
