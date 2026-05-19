

// ---------------------------------------------- //
//             DO NOT EDIT THIS CODE -            //
//         INSTEAD, CREATE A NEW FILE IN          //
//       THE TEAMCODE FOLDER, AND USE THIS        //
//               FILE AS A REFERENCE              //
// ---------------------------------------------- //


package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

// Make sure to declare your program with @Autonomous, not @TeleOP
@Autonomous(name = "Sample Code")
public class SampleCode extends LinearOpMode {

    // Declare motors
    DcMotorEx frontRight;
    DcMotorEx frontLeft;
    DcMotorEx backRight;
    DcMotorEx backLeft;

    // These variables keep track of the positions of the drive motors
    int flPosition = 0;
    int frPosition = 0;
    int blPosition = 0;
    int brPosition = 0;

    double pow;

    // Set the target position and power of the drive motors
    public void motorSet(int fl, int fr, int bl, int br, double power) {

        frontLeft.setTargetPosition(flPosition + fl);
        frontRight.setTargetPosition(frPosition + fr);
        backLeft.setTargetPosition(blPosition + bl);
        backRight.setTargetPosition(brPosition + br);

        flPosition += fl;
        frPosition += fr;
        blPosition += bl;
        brPosition += br;

        pow = power/10;

        frontLeft.setPower(pow);
        frontRight.setPower(pow);
        backLeft.setPower(pow);
        backRight.setPower(pow);

        sleep(50);

    }

    // Checks if the front two motors are still trying to get to their target position
    public boolean isDriving() {

        return (frontLeft.isBusy() && frontRight.isBusy()) || (backLeft.isBusy() && backRight.isBusy());

    }

    @Override
    public void runOpMode() throws InterruptedException {

        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");

        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setTargetPosition(0);
        frontRight.setTargetPosition(0);
        backLeft.setTargetPosition(0);
        backRight.setTargetPosition(0);

        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        // Start of the program
        waitForStart();

        motorSet(1000, 1000, 1000, 1000, 5);

        while(opModeIsActive() && isDriving()) {

            sleep(20);

        }

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

    }
}