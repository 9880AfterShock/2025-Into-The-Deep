package org.firstinspires.ftc.teamcode


import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor


object MainLift { //Prefix for commands
    private lateinit var lift: DcMotor //Init Motor Var
    var pos = 0.0 //starting Position
    var currentSpeed = 0.0 //Starting speed, WHY ARE YOU MAKING A FALLING LIFT???
    @JvmField
    var speed = 0.01 //update speed
    val encoderTicks = 1425.1 //calculate your own ratio
    @JvmField
    var minPos = 0.0 //bottom position
    @JvmField
    var maxPos = 2.0 //top position
    lateinit var opmode: OpMode //opmode var innit
    var encoderMode: DcMotor.RunMode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
    var motorMode: DcMotor.RunMode = DcMotor.RunMode.RUN_TO_POSITION //set motor mode
    fun initLift(opmode: OpMode){ //init motors
        lift = opmode.hardwareMap.get(DcMotor::class.java, "mainLift") //config name
        lift.targetPosition = (pos*encoderTicks).toInt()
        lift.mode = encoderMode //reset encoder
        lift.mode = motorMode //enable motor mode
        this.opmode = opmode
    }
    fun updateLift(){
//can change controls
        if (opmode.gamepad2.dpad_up && !opmode.gamepad2.dpad_down) {
            currentSpeed = speed
        }
        else
            if (opmode.gamepad2.dpad_down && !opmode.gamepad2.dpad_up) {
                currentSpeed = -speed
            } else {
                currentSpeed = 0.0
            }

        pos += currentSpeed

        if (pos<maxPos) {
            pos = maxPos
        }
        if (pos>minPos) {
            pos = minPos
        }
        lift.power = 1.0 //turn motor on
        lift.targetPosition = (pos*encoderTicks).toInt()
        opmode.telemetry.addData("Main Lift target position", pos) //Set telemetry
        opmode.telemetry.addData("cur speed",currentSpeed)
    }

}