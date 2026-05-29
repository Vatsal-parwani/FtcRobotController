package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/* How to use this class:
1. In another class, extend Localisation
2. Use the method "initLocalisationHardware()" in thelg init() of the OpMode
3. Use the "startLocalisation()" between the init() and loop() in the start()
4. Use "updateLocalisation()" in the loop() of the OpMode
5. Use "getPedroLocation()" wherever you want pedro coordinates
 */

@TeleOp
public class LocalisationTesting extends Localisation {

    @Override
    public void init() {
        initLocalisationHardware();
    }

    public void start(){
        startLocalisation();
    }

    @Override
    public void loop() {
        updateLocalisation();
        getPedroLocation();
    }
}
