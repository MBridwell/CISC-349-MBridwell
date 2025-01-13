public class Contractor extends Employee{
    private int Hourly;

    public Contractor(String name, String job_title, String ssn, int Hourly) {
        super(name, job_title, ssn);
        this.Hourly = Hourly;
    }

    public int getHourly() {
        return Hourly;
    }

    public void setHourly(int Hourly) {
        this.Hourly = Hourly;
    }

}
