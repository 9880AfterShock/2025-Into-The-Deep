package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor

object Raiser { //Prefix for commands
    private lateinit var motor: DcMotor //Init Motor Var
    var targetDegrees = 0.0 //starting Position
    @JvmField
    val encoderTicks = 1425.1 //calculate your own ratio
    val gearRatio = 100/20
    val upPos = 45.0 //in degrees
    val downPos = 0.0 //in degrees
    var status = 0 // bandaid
    private var downButtonCurrentlyPressed = false
    private var downButtonPreviouslyPressed = false
    private var upButtonCurrentlyPressed = false
    private var upButtonPreviouslyPressed = false
    lateinit var opmode: OpMode //opmode var innit
    var encoderMode: DcMotor.RunMode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
    var motorMode: DcMotor.RunMode = DcMotor.RunMode.RUN_TO_POSITION //set motor mode
    fun initRaiser(opmode: OpMode){ //init motors
        motor = opmode.hardwareMap.get(DcMotor::class.java, "raiser") //config name
        motor.targetPosition = (targetDegrees*encoderTicks).toInt()
        motor.mode = encoderMode //reset encoder
        motor.mode = motorMode //enable motor mode
        this.opmode = opmode
    }
    fun updateRaiser() {
        downButtonCurrentlyPressed = opmode.gamepad2.b //can change controls
        upButtonCurrentlyPressed = opmode.gamepad2.y //can change controls

        // If the button state is different than what it was, then act
        if (!(downButtonCurrentlyPressed && upButtonCurrentlyPressed)) {
            if (downButtonCurrentlyPressed && !downButtonPreviouslyPressed) {
                targetDegrees = downPos //in degrees
                status = 1 //bandaid
            } else {
                if (upButtonCurrentlyPressed && !upButtonPreviouslyPressed) {
                    targetDegrees = upPos
                    status = 0 //bandaid
                    }
                }
            }

        downButtonPreviouslyPressed = downButtonCurrentlyPressed
        upButtonPreviouslyPressed = upButtonCurrentlyPressed

        motor.setPower(1.0) //turn motor on
        motor.targetPosition = (-837 * status) //bandaid
        //motor.targetPosition = (targetDegrees*encoderTicks*gearRatio/360).toInt()
        opmode.telemetry.addData("Raiser target position", targetDegrees) //Set telemetry
        opmode.telemetry.addData("encoder ticks", motor.currentPosition) //testing -837 (bandaid)
    }

}