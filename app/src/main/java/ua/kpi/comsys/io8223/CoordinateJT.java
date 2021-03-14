package ua.kpi.comsys.io8223;

import android.annotation.SuppressLint;

public class Coordinate {

    private final Direction currentDir;
    private String worldLetter;
    private final int degree;
    private final int minute;
    private final int second;

    public Coordinate(){
        currentDir = Direction.LATITUDE;
        worldLetter = "N";
        degree = minute = second = 0;
    }

    @SuppressLint("DefaultLocale")
    public Coordinate(int deg, int min, int sec, Direction dir) throws Exception {
        currentDir = dir;
        if (dir == Direction.LATITUDE){
            if (deg >= -90 && deg <= 90){
                degree = deg;
                if (deg >= 0)
                    worldLetter = "N";
                else
                    worldLetter = "S";
            }
            else {
                System.err.println(String.format("Несумісний градус (%d) в координатах широти", deg));
                throw new Exception();
            }
        }
        else {
            if (deg >= -180 && deg <= 180){
                degree = deg;
                if (deg >= 0)
                    worldLetter = "E";
                else
                    worldLetter = "W";
            }
            else {
                System.err.println(String.format("Несумісний градус (%d) в координатах довготи", deg));
                throw new Exception();
            }
        }

        if (min >= 0 && min <= 59){
            minute = min;
        }
        else {
            System.err.println(String.format("Несумісні мінути (%d) в координатах", min));
            throw new Exception();
        }

        if (sec >= 0 && sec <= 59){
            second = sec;
        }
        else {
            System.err.println(String.format("Несумісні секунди (%d) в координатах", sec));
            throw new Exception();
        }

    }

    @SuppressLint("DefaultLocale")
    public String getIntCoordinate(){
        return String.format("%d°%d'%d\" %s", Math.abs(degree), minute, second, worldLetter);
    }

    private float getFloatSigned(){
        return (Math.abs(degree) + (float)minute/60 + (float)second/3600) * (degree >= 0? 1: -1);
    }

    @SuppressLint("DefaultLocale")
    public String getFloatCoordinate(){
        return String.format("%f° %s", Math.abs(getFloatSigned()), worldLetter);
    }

    public Coordinate getMiddleCoordinate(Coordinate a, Coordinate b) throws Exception {
        if (a.getCurrentDir() == b.getCurrentDir()){
            return new Coordinate((a.getDegree() + b.getDegree()) / 2,
                    (a.getMinute() + b.getMinute()) / 2,
                    (a.getSecond() + b.getSecond()) / 2,
                    a.getCurrentDir());
        }
        else {
            return null;
        }
    }

    public Coordinate getMiddleCoordinate(Coordinate second) throws Exception {
        return getMiddleCoordinate(this, second);
    }

    public Direction getCurrentDir() {
        return currentDir;
    }

    public int getDegree() {
        return degree;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }
}

enum Direction {
    LATITUDE,
    LONGITUDE
}