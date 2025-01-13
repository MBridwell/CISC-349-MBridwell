public class SalariedFull extends Employee{
    private int weekTimeWorked;
    private int salary;

    public SalariedFull(String name, String job_title, String ssn, int weekTimeWorked, int salary) {
        super(name, job_title, ssn);
        this.weekTimeWorked = weekTimeWorked;
        this.salary = salary;
    }

    public int getWeekTimeWorked() {
        return weekTimeWorked;
    }

    public void setWeekTimeWorked(int weekTimeWorked) {
        this.weekTimeWorked = weekTimeWorked;
    }

    public int getsalary() {
        return salary;
    }

    public void setsalary(int salary) {
        this.salary = salary;
    }
    

}