package com.rutvik.covid19tracker;

public class Model {
    private String flag, countries,cases,todayCases,Death,todayDeath,recovered,active,critical;

    public Model() {
    }

    public Model(String flag, String countries, String cases, String todayCases, String death, String todayDeath, String recovered, String active, String critical) {
        this.flag = flag;
        this.countries = countries;
        this.cases = cases;
        this.todayCases = todayCases;
        Death = death;
        this.todayDeath = todayDeath;
        this.recovered = recovered;
        this.active = active;
        this.critical = critical;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getDeath() {
        return Death;
    }

    public void setDeath(String death) {
        Death = death;
    }

    public String getTodayDeath() {
        return todayDeath;
    }

    public void setTodayDeath(String todayDeath) {
        this.todayDeath = todayDeath;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }
}
