package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.Servo


object Wrist {
    private lateinit var wrist: Servo
    @JvmField
    var positions = arrayOf (0, 90, 180) //positions, most forward to most backward
    @JvmField
    var initPos = 200 //innit pos prob 200-220 or so
    var currentPos = initPos //innit pos
    private var state = "Init"
    private var debug = 0
    private var debugmore = 0
    private var backwardWristButtonCurrentlyPressed = false
    private var backwardWristButtonPreviouslyPressed = false
    private var forwardWristButtonCurrentlyPressed = false
    private var forwardWristButtonPreviouslyPressed = false

    lateinit var opmode:OpMode

    fun initWrist(opmode: OpMode){
        currentPos = initPos //reset pos
        wrist = opmode.hardwareMap.get(Servo::class.java, "Wrist") //config name
        this.opmode = opmode
    }

    private fun updatePosition(targetPosition: Int){
        wrist.position = targetPosition.toDouble()/270
        state = targetPosition.toString()
        debug += 1
    }

    private fun changePosition(direction: String){

        if (currentPos == initPos && direction == "forward") {
            currentPos = positions[positions.size-1] //if innited, go to last in array
        } else {
            if (direction == "forward" && currentPos != positions[0]) {
                debugmore = +1
                currentPos == positions[positions.indexOf(currentPos) - 1]
            }
            if (direction == "backward" && currentPos != positions[positions.size - 1]) {
                debugmore = +1
                currentPos == positions[positions.indexOf(currentPos) + 1]
            }
        }
        updatePosition(currentPos)
    }

    fun updateWrist() {
        // Check the status of the claw button on the game pad
        forwardWristButtonCurrentlyPressed = opmode.gamepad1.right_bumper //change these to change the button
        backwardWristButtonCurrentlyPressed = opmode.gamepad1.left_bumper

        if (!(forwardWristButtonCurrentlyPressed && backwardWristButtonCurrentlyPressed)) { //safety mechanism
            if (forwardWristButtonCurrentlyPressed && !forwardWristButtonPreviouslyPressed) {
                changePosition("forward")
            }
            if (backwardWristButtonCurrentlyPressed && !backwardWristButtonPreviouslyPressed) {
                changePosition("backward")
            }
        }

        forwardWristButtonPreviouslyPressed = forwardWristButtonCurrentlyPressed
        backwardWristButtonPreviouslyPressed = backwardWristButtonCurrentlyPressed

        opmode.telemetry.addData("Wrist State", state)
        opmode.telemetry.addData("times updated", debug)
        opmode.telemetry.addData("times forwarded/backwarded", debugmore)
    }
}