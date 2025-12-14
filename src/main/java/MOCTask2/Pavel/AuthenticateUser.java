package MOCTask2.Pavel;

class AuthenticateUser  extends  User{
 private String id;

    public AuthenticateUser(String time, String id) {
        super(time);
        this.id = id;
    }



    @Override
    public void getInfo() {
        System.out.println(getTime() + " Авторизованный пользователь" + " С уникальным id " +id);
    }
}
