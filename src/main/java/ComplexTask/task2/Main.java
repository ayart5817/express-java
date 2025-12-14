package ComplexTask.task2;

public class Main {
    public static void main(String[] args) {
        VideoService videoService = new VideoService();


        //загрузка форматов
       // String id1 = videoService.uploadVideo("video1.avi");
       // String id2 = videoService.uploadVideo("video2.mov");
        //String id3 = videoService.uploadVideo("video3.wmv");
        String id6 = videoService.uploadVideo("video3.mp4");
        String id4 = videoService.uploadVideo("video4.");
        String id5 = videoService.uploadVideo("");

        //стриминг

       // videoService.streamVideo(id1);
        System.out.println();
       // videoService.streamVideo(id2);
        System.out.println();
        //videoService.streamVideo(id3);
        //videoService.streamVideo(id4);
        videoService.streamVideo(id6);

    }
}
