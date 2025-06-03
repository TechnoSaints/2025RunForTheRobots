package org.firstinspires.ftc.teamcode.opmode.auto;
import org.firstinspires.ftc.teamcode.opmode.FieldLocations;
import org.firstinspires.ftc.teamcode.opmode.Paths;

public abstract class SpecimenAutoOpMode extends AutoOpMode {

    /**
     * This method is called once at the init of the OpMode.
     **/
    @Override
    public void init() {
        FieldLocations.startPose = FieldLocations.specimenStartPose;
        super.init();
    }

    @Override
    public void start() {
        Paths.buildSpecimenPaths(bot.getFollower());
        Paths.buildHybridPaths(bot.getFollower());
    }
}

