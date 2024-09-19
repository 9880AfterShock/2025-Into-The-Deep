package org.firstinspires.ftc.teamcode.primary

import android.widget.GridLayout.Spec
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.Claw
import org.firstinspires.ftc.teamcode.MainLift
import org.firstinspires.ftc.teamcode.MecanumDriveTrain
import org.firstinspires.ftc.teamcode.Raiser
import org.firstinspires.ftc.teamcode.SpecimenClaw
import org.firstinspires.ftc.teamcode.SpecimenLift
import org.firstinspires.ftc.teamcode.SpecimenSwivel
import org.firstinspires.ftc.teamcode.Wrist

@TeleOp(name = "9880 TeleOpMode Into-the-Deep") //change string for display name
//Toggle Disabled to make appear in list or not.
//@Disabled
class TeleOpMode : LinearOpMode() {
    private val runtime = ElapsedTime()
    private var test = 0
    //Make Motor Vars

    override fun runOpMode() {
        //Add init msg
        telemetry.addData("Status", "Initialized")
        telemetry.update()

        //Call Init Functions (make sure to add "this")
        MecanumDriveTrain.initDrive(this)
        Claw.initClaw(this)
        MainLift.initLift(this)
        Raiser.initRaiser(this)
        Wrist.initWrist(this)
        SpecimenLift.initLift(this)
        SpecimenClaw.initClaw(this)
        SpecimenSwivel.initSwivel(this)
        // init commands here

        //Wait for start
        waitForStart()
        runtime.reset()

        //Running Loop
        while (opModeIsActive()) {
            //Tick Commands Here
            MecanumDriveTrain.updateDrive() //remove for testing
            Claw.updateClaw()
            MainLift.updateLift()
            Raiser.updateRaiser()
            Wrist.updateWrist()
            SpecimenLift.updateLift()
            SpecimenClaw.updateClaw()
            SpecimenSwivel.updateSwivel()

            // Show the elapsed time (and other telemetry) on driver station
            telemetry.addData("Status", "Run Time: $runtime")
            telemetry.update()
        }
    }
}