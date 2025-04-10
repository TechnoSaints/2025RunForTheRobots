package org.firstinspires.ftc.teamcode.common.vision;

import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;
import org.firstinspires.ftc.vision.opencv.ColorRange;
import org.firstinspires.ftc.vision.opencv.ImageRegion;
import org.opencv.core.RotatedRect;

import java.util.List;

public class Webcam extends VisionSensor {
    private ColorBlobLocatorProcessor colorLocator;
    private VisionPortal portal;
    private List<ColorBlobLocatorProcessor.Blob> blobs;
    private RotatedRect box;
    private final int resWidth = 320;
    private final int widthCenter = (int) (resWidth / 2.0);
    private int pixelsForwardOfCenter;
    private double inchesForwardOfCenter;
    private final int resHeight = 240;
    private final int heightCenter = (int)(resHeight/2.0);
    private int pixelsLeftOfCenter;
    private double inchesLeftOfCenter;
    double heightPixelsPerInch = 25;
    double widthPixelsPerInch = 25;

    public Webcam(HardwareMap hardwareMap, Telemetry telemetry, String name) {
        super(telemetry);
        grabberLeftOffsetInches = 0;
        grabberForwardOffsetInches = 0;
        forwardMinInches = -5;
        forwardMaxInches = 5;
        leftMinInches = -5;
        leftMaxInches = 5;

        colorLocator = new ColorBlobLocatorProcessor.Builder()
                .setTargetColorRange(ColorRange.YELLOW)         // use a predefined color match
                .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)    // exclude blobs inside blobs
                .setRoi(ImageRegion.asUnityCenterCoordinates(-1.0, 1.0, 1.0, -1.0))  // search central 1/4 of camera view
                .setDrawContours(true)                        // Show contours on the Stream Preview
                .setBlurSize(5)                               // Smooth the transitions between different colors in image
                .build();

        portal = new VisionPortal.Builder()
                .addProcessor(colorLocator)
                .setCameraResolution(new Size(resWidth, resHeight))
                .setCamera(hardwareMap.get(WebcamName.class, "armcam"))
                .build();
        start();
    }

    public boolean updateFilteredResult(double maxSenseTimeMS) {
        timer.reset();
        updateResult();
        while (((!resultIsValid()))
                && (timer.milliseconds() < maxSenseTimeMS)) {
            updateResult();
//            ColorBlobLocatorProcessor.Util.filterByArea(minArea, maxArea, blobs);
//            ColorBlobLocatorProcessor.Util.filterByDensity(minDensity, maxDensity, blobs);
//            ColorBlobLocatorProcessor.Util.filterByAspectRatio(minAspect, maxAspect, blobs);
        }
        if (resultIsValid()) {
            box = blobs.get(0).getBoxFit();
            pixelsForwardOfCenter = heightCenter - (int)box.center.y;
            pixelsLeftOfCenter =  widthCenter - (int) box.center.x;
            forwardDistanceToTargetInches = pixelsForwardOfCenter/heightPixelsPerInch;
            leftDistanceToTargetInches = pixelsLeftOfCenter/widthPixelsPerInch;
            telemetry.addData("box center x: ",box.center.x);
            telemetry.addData("box center y: ",box.center.y);
            telemetry.addData("pixels forward of center: ",pixelsForwardOfCenter);
            telemetry.addData("pixels left of center: ",pixelsLeftOfCenter);
            telemetry.update();
        }
        return (resultIsValid());
    }

    public void updateResult() {
        blobs = colorLocator.getBlobs();
    }

    protected boolean resultIsValid() {
        return (!blobs.isEmpty());
    }

    public void start() {
    }

    public void stop() {
    }
}




