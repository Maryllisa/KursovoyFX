package tables;

public class Table_read_admin {

    private String login;
    private String surname;
    private String name;
    private  String patronymic;
    private String work;

    public Table_read_admin(String login, String surname, String name, String patronymic, String work) {
        this.login = login;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.work = work;
    }

    public String getLogin() {
        return login;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getWork() {
        return work;
    }
}
