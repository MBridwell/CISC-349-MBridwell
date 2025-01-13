public class Employee {
    String name ;
    String job_title;
    String ssn;
    

    public Employee(String name, String job_title, String ssn){
        this.name = name;
        this.job_title = job_title;
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getjob_title() {
        return job_title;
    }

    public void setjob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}