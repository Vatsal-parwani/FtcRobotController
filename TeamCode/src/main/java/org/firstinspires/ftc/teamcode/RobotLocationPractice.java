package org.firstinspires.ftc.teamcode.mycode;

public class RobotLocationPractice {

    double angle;
    double x;
    double y;

    //constructor method
    public RobotLocationPractice(double angle, double x, double y) {
        this.angle = angle;
        this.x = x;
        this.y = y;
    }

    public double getAngle() {
        return this.angle;
    }

    public double getHeading() {
        //normalises robot heading between -180 and 180
        //useful for calculating turn angles especially when crossing the 0 - 360 boundary

        double angle = this.angle;
        while (angle > 180) {
            angle -= 360;

        }
        while (angle < -180) {
            angle += 360;
        }
        return angle;
    }

    public void turnRobot(double angleChange) {
        angle += angleChange;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getX() {
        return this.x;
    }

    public void changeX(double changeAmount) {
        x += changeAmount;

    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void changeY(double changeAmount) {
        y += changeAmount;

    }

    public void setY(double x) {
        this.y = y;
    }

}
