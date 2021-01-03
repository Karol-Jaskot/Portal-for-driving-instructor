package pl.jaskot.portalfordrivinginstructor.Backend.entity;

import org.springframework.util.StopWatch;

public class MyTimer {

    private StopWatch stopWatch;
    int minutes;
    int seconds;
    int overTime;
    long currentTime;

    public MyTimer(){
        stopWatch = new StopWatch();
        overTime = 25;
        minutes = 0;
        seconds = 0;
    }

    public void start(){
        stopWatch.start();
    }

    public void stop(){
        stopWatch.stop();
    }

    public boolean check(){
        return overTime < minutes;
    }

    public boolean isRunning(){
        return stopWatch.isRunning();
    }

    public void reset(){
        seconds = 0;
        minutes = 0;
        stopWatch = new StopWatch();
    }

    public String getValue(){
        currentTime = (long) stopWatch.getTotalTimeSeconds();
        minutes = (int) currentTime / 60;
        seconds = (int) currentTime % 60;
        if(minutes == 1){
            return "Upłyneła "+minutes+" minuta "+seconds+" sekund z 25 minut";
        }else if(minutes == 2 || minutes == 3 || minutes == 4){
            return "Upłyneły "+minutes+" minuty "+seconds+" sekund z 25 minut";
        }else {
            return "Upłyneło "+minutes+" minut "+seconds+" sekund z 25 minut";
        }
    }
}
