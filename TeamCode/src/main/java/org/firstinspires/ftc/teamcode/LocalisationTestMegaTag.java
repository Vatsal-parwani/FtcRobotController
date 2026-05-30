package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class LocalisationTestMegaTag extends LocalisationMegaTag{

    @Override
    public void init() {
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
