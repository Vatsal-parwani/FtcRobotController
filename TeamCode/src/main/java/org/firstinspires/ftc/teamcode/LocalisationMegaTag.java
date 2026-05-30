package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

public abstract class LocalisationMegaTag extends OpMode {

    public Limelight3A limelight;
    public com.pedropathing.follower.Follower follower;

    // MT1 Filter Thresholds
    public double minimumTa = 0.2;
    public double maximumTx = 20.0; // Bumped to 20 to give you a wider usable FOV
    public double maximumDistanceJumped = 6.0; // Tightened track limits to 6 inches

    //@Override
    public void init() {
    }

    //@Override
    public void loop() {
    }

    public void initHardware(){
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(8);
    }

    public void startLimelight(){
        limelight.start();
    }

    public void updateLocation(){
        LLResult llResult = limelight.getLatestResult();

        if(llResult != null && llResult.isValid()){
            double ta = llResult.getTa();
            double tx = llResult.getTx();

            // 1. Lens Distortion Filter
            if (ta > minimumTa && Math.abs(tx) < maximumTx) {
                Pose3D botpose = llResult.getBotpose();

                double limelightXinInch = botpose.getPosition().x * 100*0.3937;
                double limelightYinInch = botpose.getPosition().y *100*0.3937;

                // 2. Proximity Check against Pedro odometry
                double currentX = follower.getPose().getX();
                double currentY = follower.getPose().getY();
                double distanceDelta = Math.hypot(limelightXinInch - currentX, limelightYinInch - currentY);

                // 3. High-Trust Exception or standard proximity pass
                if (distanceDelta < maximumDistanceJumped || ta > 1.5) {
                    telemetry.addData("MT1 Status", "Accepted");
                    //telemetry.addData("Limelight X (inch)", limelightXinInch);
                    //telemetry.addData("Limelight Y (inch)", limelightYinInch);
                } else {
                    telemetry.addData("MT1 Status", "Rejected - Jump > 6 inches");
                }
            } else {
                telemetry.addData("MT1 Status", "Rejected - Tag too small/off-center");
            }
        }
        else{
            telemetry.addData("Limelight Pose", false);
        }

        telemetry.update();
    }
}