package tables;


public class Table_filters_step {
  private float coef;
  private String fio_student;
  private  String group;
  private String id_student;
  private int missing;
  private float raiting;
  private float step;

    public Table_filters_step(float coef, String fio_student, String group, String id_student, int missing, float raiting, float step) {
        this.coef = coef;
        this.fio_student = fio_student;
        this.group = group;
        this.id_student = id_student;
        this.missing = missing;
        this.raiting = raiting;
        this.step = step;
    }

    public float getCoef() {
        return coef;
    }

    public String getFio_student() {
        return fio_student;
    }

    public String getGroup() {
        return group;
    }

    public String getId_student() {
        return id_student;
    }

    public int getMissing() {
        return missing;
    }

    public float getRaiting() {
        return raiting;
    }

    public float getStep() {
        return step;
    }
}
