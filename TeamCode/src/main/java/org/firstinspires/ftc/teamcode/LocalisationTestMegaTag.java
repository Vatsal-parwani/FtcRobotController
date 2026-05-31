package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@TeleOp
public class LocalisationTestMegaTag extends LocalisationMegaTag{

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);
        initHardware();
    }

    @Override
    public void start() {
        startLimelight();
    }

    @Override
    public void loop() {
        updateLocation();
    }
}
