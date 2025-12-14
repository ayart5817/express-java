package MOCTask2.Pavel;

public class AnonimusUser extends  User{


    public AnonimusUser(String time ) {
        super(time);

    }



    @Override
    public void getInfo() {
        System.out.println(getTime() + " Анонимный пользователь" );
    }
}
