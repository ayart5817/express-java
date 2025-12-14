package ComplexTask.task2;

public class WMVVideoAdapter implements VideoAdapter {
    @Override
    public String convertToMP4(String file)  {
        System.out.println("Конвертация WMV->MP4");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Конвертация завершена\n");
        return file.toLowerCase().replace(".wmv", ".mp4");
    }
}