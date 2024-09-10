package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.util.Range

object MacanumDriveTrain { //Prefix for commands
    private lateinit var leftRear: DcMotor //init motor vars
    private lateinit var leftFront: DcMotor
    private lateinit var rightRear: DcMotor
    private lateinit var rightFront: DcMotor
    lateinit var opmode: OpMode //opmode var innit
    var currentSpeedDivider = 1.0 //for slow mode
    private var slowModeButtonCurrentlyPressed = false
    private var slowModeButtonPreviouslyPressed = false
    fun initDrive(opmode: OpMode){ //init motors
        leftRear = opmode.hardwareMap.get(DcMotor::class.java, "leftRear") //motor config names
        leftFront = opmode.hardwareMap.get(DcMotor::class.java,"leftFront")
        rightRear = opmode.hardwareMap.get(DcMotor::class.java, "rightRear")
        rightFront = opmode.hardwareMap.get(DcMotor::class.java, "rightFront")

        leftRear.direction = DcMotorSimple.Direction.REVERSE //motor directions
        leftFront.direction = DcMotorSimple.Direction.REVERSE
        rightRear.direction = (DcMotorSimple.Direction.FORWARD)
        rightFront.direction = (DcMotorSimple.Direction.FORWARD)

        this.opmode = opmode
    }
    fun updateDrive(){
        var leftBackPower: Double
        var leftFrontPower: Double
        var rightBackPower: Double
        var rightFrontPower: Double

        val strafe = opmode.gamepad1.left_stick_x.toDouble() //can change controls
        val drive = -opmode.gamepad1.left_stick_y.toDouble()
        val turn = opmode.gamepad1.right_stick_x.toDouble()
        leftBackPower = Range.clip(drive + turn - strafe, -1.0, 1.0)
        leftFrontPower = Range.clip(drive + turn + strafe, -1.0, 1.0)
        rightBackPower = Range.clip(drive - turn + strafe, -1.0, 1.0)
        rightFrontPower = Range.clip(drive - turn - strafe, -1.0, 1.0)

        updateSpeed(3.0) //set to the slowmode divider

        leftRear.power = leftBackPower/currentSpeedDivider
        leftFront.power = leftFrontPower/currentSpeedDivider
        rightRear.power = rightBackPower/currentSpeedDivider
        rightFront.power = rightFrontPower/currentSpeedDivider

        opmode.telemetry.addData("Front Motors", "left (%.2f), right (%.2f)", leftFrontPower, rightFrontPower)
        opmode.telemetry.addData("Back Motors", "left (%.2f), right (%.2f)", leftBackPower, rightBackPower)
        opmode.telemetry.addData("Divider", "divider (%.2f)", currentSpeedDivider)
    }
    private fun updateSpeed(speedDivider: Double){
        // Check the status of the speed button on the gamepad
        slowModeButtonCurrentlyPressed = (opmode.gamepad1.right_trigger.toDouble() != 0.0)//change this to change the button

        // If the button state is different than what it was, then act
        if (slowModeButtonCurrentlyPressed != slowModeButtonPreviouslyPressed) {
            // If the button is (now) down
            if (slowModeButtonCurrentlyPressed) {
                swapSpeed(speedDivider)
            }
        }
        slowModeButtonPreviouslyPressed = slowModeButtonCurrentlyPressed
    }

    private fun swapSpeed(speedDivider: Double) {
        if (currentSpeedDivider == speedDivider) {
            currentSpeedDivider == 1.0
        } else {
            currentSpeedDivider == speedDivider
        }
    }
}