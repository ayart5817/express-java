package FreeTask.static_final;

public class CountryFixCode {
    private final String code;

    public CountryFixCode(String code) {
        this.code = code.toUpperCase().trim();
    }
    public String getCode() {
        return code;
    }



    static void main() {
        CountryFixCode Russia = new CountryFixCode("ru");
        System.out.println(Russia.getCode());
    }

}
