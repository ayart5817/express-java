package MOCTask2.Pavel;

class CorporateUser extends  User{
    private String id;
    private String corporate;

    public CorporateUser(String time, String id, String corporate) {
        super(time);
        this.id = id;
        this.corporate = corporate;
    }



    @Override
    public void getInfo() {
        System.out.println(getTime() + "  Корпоративный пользователь " + corporate + " С уникальным id " +id);
    }
}
