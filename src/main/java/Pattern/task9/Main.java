package Pattern.task9;

public class Main {
    static void main(String[] args) {
        DocumentAdapter adapter = new DocumentAdapter(new DOCProcessor());

        adapter.process("Отчет.pdf");
        adapter.process("Отчет2.doc");
        adapter.process("Отчет2.do");
    }
}
