package com.SanpedroAlatraca.project.work_it_out;

public class VitalModel {
    String pressure, temp, heartrate, respiratory;

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHeartrate() {
        return heartrate;
    }

    public void setHeartrate(String heartrate) {
        this.heartrate = heartrate;
    }

    public String getRespiratory() {
        return respiratory;
    }

    public void setRespiratory(String respiratory) {
        this.respiratory = respiratory;
    }

    public VitalModel() {

    }

    public VitalModel(String pressure, String temp, String heartrate, String respiratory) {

        this.pressure = pressure;
        this.temp = temp;
        this.heartrate = heartrate;
        this.respiratory = respiratory;
    }
}
