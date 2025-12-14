package Pattern.task9;

//DOCProcessor.java
public class DOCProcessor implements DocumentProcessor {
    @Override
    public void process(String filename) {
        if (!filename.endsWith(".doc")) {
            throw new IllegalArgumentException("Только формат .doc");
        }
        System.out.println("Обработан DOC файл:" + filename);
    }
}
