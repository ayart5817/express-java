package Pattern.task9;

public class PDFProcessor implements DocumentProcessor {

    @Override
    public void process(String filename) {
        if (!filename.endsWith(".pdf")) {
            throw new IllegalArgumentException("только PDF формат");
        }
        System.out.println("Обработан PDF файл:" + filename);
    }
}
