package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

/* How to use this class:
1. In another class, extend Localisation
2. Use the method "initLocalisationHardware()" in thelg init() of the OpMode
3. Use the "startLocalisation()" between the init() and loop() in the start()
4. Use "updateLocalisation()" in the loop() of the OpMode
5. Use "getPedroLocation()" wherever you want pedro coordinates
 */

@TeleOp
public class ChubIMUTesting extends LocalisationCHubIMU {

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);

        // --- YOUR STARTING GRID POSITION ---
        // Setting Pedro's origin to the exact center of the FTC field (72, 72)
        Pose startPose = new Pose(72.0, 72.0, Math.toRadians(0.0));
        follower.setStartingPose(startPose);
        // -----------------------------------

        initLocalisationHardware();
    }

    public void start(){
        startLocalisation();
    }

    @Override
    public void loop() {
        follower.update();
        updateLocalisation();
        //getPedroLocation();
    }
}
