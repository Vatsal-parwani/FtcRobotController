package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class IntakeShooterTest extends OpMode {
    @Override
    public void init() {

    }

    @Override
    public void loop() {
        DcMotor intakeMotor = hardwareMap.get(DcMotor.class, "intake");
        DcMotor shooterMotorA = hardwareMap.get(DcMotor.class, "shooterA");
        DcMotor shooterMotorB = hardwareMap.get(DcMotor.class, "shooterB");

        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        if (gamepad1.a){
            intakeMotor.setPower(1);
        }

        if (gamepad1.b){
            intakeMotor.setPower(0);
        }

        if (gamepad1.x){
            shooterMotorA.setPower(1);
            shooterMotorB.setPower(1);
        }

        if (gamepad1.y){
            shooterMotorA.setPower(0);
            shooterMotorB.setPower(0);
        }
    }
}
