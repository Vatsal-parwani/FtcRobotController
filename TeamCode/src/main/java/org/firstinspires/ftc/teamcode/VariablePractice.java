package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class VariablePractice extends OpMode {
    @Override
    public void init(){
        int teamNumber = 33001;
        double motorSpeed = 0.75;
        boolean shooterOn = true; //Booleans must be lowercase
        String teamName = "WilSonic Boom"; //String is a class, must start with capitals
        int hoodAngle = 50;

        telemetry.addData("Team Number", teamNumber); //To print to driver hub, use ".addData"
        telemetry.addData("Motor Speed", motorSpeed);
        telemetry.addData("Shooter On", shooterOn);
        telemetry.addData("My name is", teamName);
        telemetry.addData("Motor angle", hoodAngle);
    }

    public void loop(){

    }
}
