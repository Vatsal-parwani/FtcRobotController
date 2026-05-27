package org.firstinspires.ftc.teamcode.mycode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class UseRobotLocationOpMode extends OpMode {
    org.firstinspires.ftc.teamcode.mycode.RobotLocationPractice robotLocationPractice = new org.firstinspires.ftc.teamcode.mycode.RobotLocationPractice(0, 0, 0);


    @Override
    public void init() {
        robotLocationPractice.setAngle(0);
        robotLocationPractice.setX(0);
        robotLocationPractice.setY(0);
    }

    @Override
    public void loop(){
        if (gamepad1.a){
            robotLocationPractice.turnRobot(20);
        }
        else if (gamepad1.b){
            robotLocationPractice.turnRobot(-20);
        }

        if (gamepad1.dpad_left){
            robotLocationPractice.changeX(0.1);
        }

        else if (gamepad1.dpad_right){
            robotLocationPractice.changeX(-0.1);
        }

        if (gamepad1.dpad_up){
            robotLocationPractice.changeY(-0.1);
        }

        else if (gamepad1.dpad_down){
            robotLocationPractice.changeY(0.1);
        }

        telemetry.addData("Heading", robotLocationPractice.getHeading());
        telemetry.addData("Angle", robotLocationPractice.getAngle());

        telemetry.addData("X", robotLocationPractice.getX());
        telemetry.addData("Y", robotLocationPractice.getY());
    }
}

