package org.firstinspires.ftc.teamcode.primary

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.Claw
import org.firstinspires.ftc.teamcode.MainLift
import org.firstinspires.ftc.teamcode.MecanumDrive


@TeleOp(name = "9880 TeleOpMode") //change string for display name
//Toggle Disabled to make appear in list or not.
//@Disabled
class TeleOpMode : LinearOpMode() {
    private val runtime = ElapsedTime()
    //Make Motor Vars

    override fun runOpMode() {
        //Add init msg
        telemetry.addData("Status", "Initialized")
        telemetry.update()

        //Call Init Functions (make sure to add "this")
        MecanumDrive.initDrive(this)
        Claw.initClaw(this)
        MainLift.initLift(this)
        // init commands here

        //Wait for start
        waitForStart()
        runtime.reset()

        //Running Loop
        while (opModeIsActive()) {
            //Tick Commands Here
            MecanumDrive.updateDrive()
            Claw.updateClaw()
            MainLift.updateLift()
            // Show the elapsed time (and other telemetry) on driver station
            telemetry.addData("Status", "Run Time: $runtime")
            telemetry.update()
        }
    }
}