package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.Servo


object Claw {
    private lateinit var claw: Servo
    @JvmField
    var openPos = 0.6 //the positions
    @JvmField
    var closePos = 1.0 //the positions
    private var state = "Open"
    private var clawButtonCurrentlyPressed = false
    private var clawButtonPreviouslyPressed = false

    lateinit var opmode:OpMode
    fun initClaw(opmode: OpMode){
        claw = opmode.hardwareMap.get(Servo::class.java, "Claw") //config name
        this.opmode = opmode
    }
    private fun open(){
        claw.position = openPos
        state = "Open"
    }
    private fun close(){
        claw.position = closePos
        state = "Close"
    }
    private fun swap(){
        if (state == "Open") {

            close()
        } else {
            open()
        }
    }
    fun updateClaw() {
        opmode.telemetry.addData("Claw State", state)

        // Check the status of the claw button on the gamepad
        clawButtonCurrentlyPressed = opmode.gamepad1.a //change this to change the button

        // If the button state is different than what it was, then act
        if (clawButtonCurrentlyPressed != clawButtonPreviouslyPressed) {
            // If the button is (now) down
            if (clawButtonCurrentlyPressed) {
                swap()
            }
        }
        clawButtonPreviouslyPressed = clawButtonCurrentlyPressed


    }
}