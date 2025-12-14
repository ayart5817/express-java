package ComplexTask2.Task3;

public class Main {
    public static void main(String[] args) throws InvalidGradeException {
        GradeService<Double> service = new GradeService<>();
        service.addGrade(new StudentGrade<>("Иван", "География", 4.99));
        service.addGrade(new StudentGrade<>("Иван", "Математика", 3.0));
        service.addGrade(new StudentGrade<>("Иван", "Физика", 4.99));
        service.addGrade(new StudentGrade<>("Маша", "География", 3.2));
        service.addGrade(new StudentGrade<>("Маша", "Математика", 5.0));
        service.addGrade(new StudentGrade<>("Маша", "Физика", 2.0));

        double avg = service.avgGrade("Математика");
        System.out.println("Средний бал Математика " + avg);

        double avg2 = service.avgGrade("География");
        System.out.println("Средний бал География " + avg2);
    }
}
