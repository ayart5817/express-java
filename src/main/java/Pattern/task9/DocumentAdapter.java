package Pattern.task9;

public class DocumentAdapter implements DocumentProcessor {
    private final DOCProcessor docProcessor;

    public DocumentAdapter(DOCProcessor docProcessor) {
        this.docProcessor = docProcessor;
    }


    @Override
    public void process(String filename) {
        if (filename.endsWith(".doc")) {
            docProcessor.process(filename);
        } else if (filename.endsWith(".pdf")) {
            String newFile = filename.substring(0, filename.length() - 4) + ".doc";
            System.out.println("Преобразование " + filename + " PDF->DOC");
            docProcessor.process(newFile);
        } else {
            System.out.println("Ошибка обработки: неизвестный формат");
        }
    }
}
