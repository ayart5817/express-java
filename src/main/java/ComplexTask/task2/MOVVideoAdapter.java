package ComplexTask.task2;

public class MOVVideoAdapter implements VideoAdapter {
    @Override
    public String convertToMP4(String file)  {
            System.out.println("Конвертация MOV->MP4");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Конвертация завершена\n");
            return file.toLowerCase().replace(".mov", ".mp4");
        }
    }
