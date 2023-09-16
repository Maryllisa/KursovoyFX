package tables;

public class Table_read_student {

    private String id_student;
    private String name;
    private int day;
    private int monthe;
    private int year;
    private String  id_group;
    private int  col_passes;
    private float  rating;

    public Table_read_student(String id_student, String name, int day, int month, int year, String id_group, int col_passes, float rating) {
        this.id_student = id_student;
        this.name = name;
        this.day = day;
        this.monthe = month;
        this.year = year;
        this.id_group = id_group;

        this.col_passes = col_passes;
        this.rating = rating;
    }

    public String getId_student() {
        return id_student;
    }

    public String getName() {
        return name;
    }

    public String getId_group() {
        return id_group;
    }

    public int getCol_passes() {
        return col_passes;
    }

    public float getRating() {
        return rating;
    }

    public String getAge() {
        return this.day +"."+ this.monthe +"."+this.year;
    }

}
