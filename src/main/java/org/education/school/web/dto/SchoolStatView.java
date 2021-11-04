package org.education.school.web.dto;

public class SchoolStatView {

    private int alumni;
    private int yearsTeaching;
    private int dau;
    private int teachers;
    private int jobsFound;

    public SchoolStatView() {
    }

    public SchoolStatView(int alumni, int yearsTeaching, int dau, int teachers, int jobsFound) {
        this.alumni = alumni;
        this.yearsTeaching = yearsTeaching;
        this.dau = dau;
        this.teachers = teachers;
        this.jobsFound = jobsFound;
    }

    public int getAlumni() {
        return alumni;
    }

    public void setAlumni(int alumni) {
        this.alumni = alumni;
    }

    public int getYearsTeaching() {
        return yearsTeaching;
    }

    public void setYearsTeaching(int yearsTeaching) {
        this.yearsTeaching = yearsTeaching;
    }

    public int getDau() {
        return dau;
    }

    public void setDau(int dau) {
        this.dau = dau;
    }

    public int getTeachers() {
        return teachers;
    }

    public void setTeachers(int teachers) {
        this.teachers = teachers;
    }

    public int getJobsFound() {
        return jobsFound;
    }

    public void setJobsFound(int jobsFound) {
        this.jobsFound = jobsFound;
    }
}
