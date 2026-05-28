package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

public class AprilTagLlTest extends OpMode {

    private Limelight3A limelight; // class scope variable of the limelight
    private GoBildaPinpointDriver pinpoint; // same thing for pinpoint

    @Override
    public void init() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight"); // this searches the Control Hub's config. profile to find the device. The deviceName should be exactly as named in the profile
        limelight.pipelineSwitch(8); // switches to the correct configured pipeline. I have used 8 for all April Tags

        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint"); // same thing as limelight, just for pinpoint now
        //pinpoint.resetPosAndIMU(); //reset position and calibrate IMU for accuracy  NEED TO FIND A WAY TO DO THIS SAFELY
    }

    @Override
    public void start() { // required method for the limelight
        limelight.start(); // starts up the limelight. If there is delay, move to init() method
    }

    @Override
    public void loop() {

        pinpoint.update(); // update for latest readings
        double headingRadians = pinpoint.getHeading(); //get the heading, returns in radians
        double headingDegrees = Math.toDegrees(headingRadians); // convert radians to degrees

        limelight.updateRobotOrientation(headingDegrees); // feed the heading to the limelight

        LLResult llResult = limelight.getLatestResult();

        if(llResult != null && llResult.isValid()){
            Pose3D botpose = llResult.getBotpose_MT2();
            telemetry.addData("Tx", llResult.getTx()); // horizontal angle from center of camera lens to center of AprilTag
            telemetry.addData("Ty", llResult.getTy()); // vertical angle from center of camera lens to Tag
            telemetry.addData("Ta", llResult.getTa()); // percentage of image that the Tag takes, if closer, then Ta larger, if further, Ta smaller

            double limelightXInCentimeters = botpose.getPosition().x * 100; // gets the limelight x position and converts into centimeters
            double limelightYInCentimeters = botpose.getPosition().y * 100; // gets the limelight y position and converts into centimeters

            double pinpointX = limelightXInCentimeters - 180.0; // changes the x position to account for the differences in limelight and pinpoint positioning
            double pinpointY = limelightYInCentimeters - 180.0; // limelight 0,0 is at the blue goal while pinpoint 0,0 is at the centre

            pinpoint.setPosition(new Pose2D(DistanceUnit.CM, pinpointX, pinpointY, AngleUnit.DEGREES, headingDegrees)); /* updates pinpoint pose
            also updates the heading because the heading could've changed, meaning that the pose would be from different timings */

            telemetry.addData("X (cm)", pinpointX); //sends all telemetry to the driver station
            telemetry.addData("Y (cm)", pinpointY);
            telemetry.addData("Heading (deg)", headingDegrees);

        }

    }

}