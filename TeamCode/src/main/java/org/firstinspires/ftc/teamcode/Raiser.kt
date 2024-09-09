package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor

object Raiser { //Prefix for commands
    private lateinit var motor: DcMotor //Init Motor Var
    var pos = 0.0 //starting Position
    @JvmField
    val encoderTicks = 384.5 //calculate your own ratio
    val gearRation = 100/1
    private var downButtonCurrentlyPressed = false
    private var downButtonPreviouslyPressed = false
    private var upButtonCurrentlyPressed = false
    private var upButtonPreviouslyPressed = false
    lateinit var opmode: OpMode //opmode var innit
    var motorMode: DcMotor.RunMode = DcMotor.RunMode.RUN_TO_POSITION //set motor mode
    fun initRaiser(opmode: OpMode){ //init motors
        motor = opmode.hardwareMap.get(DcMotor::class.java, "raiser") //config name
        motor.targetPosition = (pos*encoderTicks).toInt()
        motor.mode = motorMode
        this.opmode = opmode
    }
    fun updateRaiser() {
//can change controls
        downButtonCurrentlyPressed = opmode.gamepad1.left_bumper
        upButtonCurrentlyPressed = opmode.gamepad1.right_bumper

        // If the button state is different than what it was, then act
        if (!(downButtonCurrentlyPressed && upButtonCurrentlyPressed)) {
            if (downButtonCurrentlyPressed && !downButtonPreviouslyPressed) {
                currentPos =- 1
                if (currentPos < 0) {
                    currentPos = 0
                }
            } else {
                if (upButtonCurrentlyPressed && !upButtonPreviouslyPressed) {
                    currentPos =+ 1
                    if (currentPos > positions.size + 1) {
                        currentPos = positions.size + 1
                    }
                }
            }
        }
        downButtonPreviouslyPressed = downButtonCurrentlyPressed
        upButtonPreviouslyPressed = upButtonCurrentlyPressed


        pos = positions[currentPos]

        motor.setPower(1.0) //turn motor on
        motor.targetPosition = (pos*encoderTicks).toInt()
        opmode.telemetry.addData("Motor list position", currentPos) //Set telemetry
        opmode.telemetry.addData("Motor target position", pos) //Set telemetry
    }

}