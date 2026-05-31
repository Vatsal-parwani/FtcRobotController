package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

import com.pedropathing.geometry.Pose;
import com.pedropathing.ftc.PoseConverter;
import com.pedropathing.ftc.FTCCoordinates;
import com.pedropathing.geometry.PedroCoordinates;

/* How to use this class:
1. In another class, extend Localisation
2. Use the method "initLocalisationHardware()" in the init() of the OpMode
3. Use the "startLocalisation()" between the init() and loop() in the start()
4. Use "updateLocalisation()" in the loop() of the OpMode
5. Use "getPedroLocation()" wherever you want pedro coordinates
 */

public abstract class Localisation extends OpMode { //abstract means that it can be used to extend in any other class


    public Limelight3A limelight; // class scope variable of the limelight

    public com.pedropathing.follower.Follower follower;

    public Pose currentPedroPose = new Pose(0, 0, 0); // Stores the active Pedro pose to avoid re-calculating

    public void initLocalisationHardware() { //method to use in the init of the other class
        limelight = hardwareMap.get(Limelight3A.class, "limelight"); // this searches the Control Hub's config. profile to find the device. The deviceName should be exactly as named in the profile
        limelight.pipelineSwitch(8); // switches to the correct configured pipeline. I have used 8 for all April Tags
    }

    /// no problems here
    public void startLocalisation() { // required method for the limelight
        limelight.start(); // starts up the limelight. If there is delay, move to init() method
    }

    /// no problems here
    public void updateLocalisation() { //method to use in the loop other class

        double headingDegrees = Math.toDegrees(follower.getHeading());
/// no problems here

        // Add the 180 offset, then normalize it to wrap properly between -180 and +180
        double offsetHeading = headingDegrees + 180.0;
        double normalizedHeading = AngleUnit.normalizeDegrees(offsetHeading);

        limelight.updateRobotOrientation(normalizedHeading); // feed the clamped, flipped heading
        telemetry.addData("Limelight Heading (deg)", normalizedHeading);

/// no problems here
        LLResult llResult = limelight.getLatestResult();
/// no problems here
        if (llResult != null && llResult.isValid()) {
            Pose3D botpose = llResult.getBotpose_MT2();
            //telemetry.addData("Tx", llResult.getTx()); // horizontal angle from center of camera lens to center of AprilTag
            //telemetry.addData("Ty", llResult.getTy()); // vertical angle from center of camera lens to Tag
            //telemetry.addData("Ta", llResult.getTa()); // percentage of image that the Tag takes, if closer, then Ta larger, if further, Ta smaller

            //double limelightXInCentimeters = botpose.getPosition().x * 100; // gets the limelight x position and converts into centimeters
            //double limelightYInCentimeters = botpose.getPosition().y * 100; // gets the limelight y position and converts into centimeters

            double limelightX = botpose.getPosition().x * 100;
            double limelightY = botpose.getPosition().y * 100;

            telemetry.addData("Limelight X (cm)", limelightX); //sends all telemetry to the driver station
            telemetry.addData("Limelight Y (cm)", limelightY);
        }
//        else{
//                telemetry.addData("Please Work", false);
//            }

//            double pinpointX = limelightXInCentimeters - 182.88; // changes the x position to account for the differences in limelight and pinpoint positioning
//            double pinpointY = limelightYInCentimeters - 182.88; // limelight 0,0 is at the blue goal while pinpoint 0,0 is at the centre
//
//            telemetry.addData("Pinpoint X (cm)", pinpointX); //sends all telemetry to the driver station
//            telemetry.addData("Pinpoint Y (cm)", pinpointY);
//            telemetry.addData("Pinpoint Heading (deg)", headingDegrees);
//
//            double limelightXInInches = botpose.getPosition().x * 39.37; // meters to inches
//            double limelightYInInches = botpose.getPosition().y * 39.37; // meters to inches
//
//            // Limelight (Blue Goal Origin) to FTC Standard (Center Origin)
//            double ftcX = limelightXInInches;// - 70.866; // Shift 1.8m (70.866in) to center
//            double ftcY = limelightYInInches;// - 70.866; // Shift 1.8m (70.866in) to center
//
//            // Generate the FTC Pose and convert to Pedro Coordinates (0-144, center 72,72)
//            Pose2D ftcPose = new Pose2D(DistanceUnit.INCH, ftcX, ftcY, AngleUnit.DEGREES, headingDegrees); // x/y/heading is created to be ftc standard
//            Pose ftcStandard = PoseConverter.pose2DToPose(ftcPose, FTCCoordinates.INSTANCE); // flags the line above as the ftc standard
//            currentPedroPose = ftcStandard.getAsCoordinateSystem(PedroCoordinates.INSTANCE); // maps ftc standard to the pedro corner origin
//
//        }
//
//    }
//
//    public Pose getPedroLocation(){ // method to use to get the co-ordinates that pedro supports
//        telemetry.addData("Pedro X (in)", currentPedroPose.getX());
//        telemetry.addData("Pedro Y (in)", currentPedroPose.getY());
//        return currentPedroPose;
//    }
//
//    @Override
//    public void init() {} //only because it is needed in OpMode
//
//    @Override
//    public void loop() {} //only because needed

    }
}