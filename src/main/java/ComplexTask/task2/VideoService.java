package ComplexTask.task2;

public class VideoService {
    public String uploadVideo(String file) {
        System.out.println("Загрузка файла: " + file);
        VideoAdapter adapter = chooseAdapter(file);
        if (adapter == null) {
            throw new IllegalArgumentException("Не подходящий формат");
        }
        String mp4 = adapter.convertToMP4(file);
        return mp4;
    }

    public void streamVideo(String video) {
        generateVideo(video);
        System.out.println("Старт стриминга видео " + video);
        System.out.println("Старт видео в поток");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Воспроизведение видео!");
    }

    private VideoAdapter chooseAdapter(String file) {

        int lastDotIndex = file.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == file.length() - 1) {
            throw new IllegalArgumentException("Файл без корректного расширения: " + file);
        }
        String name = file.substring(lastDotIndex).toLowerCase();
        switch (name) {
            case ".avi" -> {
                return new AVIVideoAdapter();
            }
            case ".mov" -> {
                return new
                        MOVVideoAdapter();
            }
            case ".wmv" -> {
                return new WMVVideoAdapter();
            }
            case ".mp4" -> {
                System.out.println("Видео не нуждается в конвертации");
                return null;
            }
            default -> throw new IllegalArgumentException("Нет поддерживаемого формата");
        }

    }

    private String generateVideo(String video) {
        System.out.println("Генерация видео" + video);
        return video;
    }
}
