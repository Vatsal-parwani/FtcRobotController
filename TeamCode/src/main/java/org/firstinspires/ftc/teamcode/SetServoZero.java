package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class SetServoZero extends LinearOpMode {


    @Override
    public void runOpMode() {
        Servo hood = hardwareMap.get(Servo.class, "hood");
        waitForStart();
        hood.setPosition(0);
        while (opModeIsActive()) {
            if (gamepad1.dpad_down) {
                hood.setPosition(hood.getPosition()-0.01);
            }
            if (gamepad1.dpad_up) {
                hood.setPosition(hood.getPosition()+0.01);
            }

            telemetry.addData("Servo Position", hood.getPosition());
        }
    }
}
