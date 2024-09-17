package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.Servo


object Wrist {
    private lateinit var wrist: Servo
    @JvmField
    var positions = arrayOf (0, 90, 180) //positions
    @JvmField
    var initPos = 0 //innit pos prob 200-220 or so
    var currentPos = initPos //innit pos
    private var state = "Init"
    private var backwardWristButtonCurrentlyPressed = false
    private var backwardWristButtonPreviouslyPressed = false
    private var forwardWristButtonCurrentlyPressed = false
    private var forwardWristButtonPreviouslyPressed = false

    lateinit var opmode:OpMode
    fun initWrist(opmode: OpMode){
        var currentPos = initPos //reset pos
        wrist = opmode.hardwareMap.get(Servo::class.java, "Wrist") //config name
        this.opmode = opmode
    }
    private fun updatePosition(targetPosition: Int){
        wrist.position = targetPosition.toDouble()/270
        state = targetPosition.toString()
    }
    private fun changePosition(direction: String){
        if (currentPos == initPos && direction == "forward") {
            currentPos = positions[positions.size-1]
        } else {
            if (direction == "forward" && !(positions.indexOf(currentPos) == positions[0]) || (positions.indexOf(currentPos) == positions[positions.size-1])) { //set limits
                currentPos = positions[positions.indexOf(currentPos)-1]
            } else {
                currentPos = positions[positions.indexOf(currentPos)+1]
            }
        }
        updatePosition(currentPos)
    }
    fun updateWrist() {
        opmode.telemetry.addData("Wrist State", state)
        opmode.telemetry.addData("Wrist POSITION", wrist.position)


        // Check the status of the claw button on the gamepad
        forwardWristButtonCurrentlyPressed = opmode.gamepad1.right_bumper //change this to change the button
        backwardWristButtonCurrentlyPressed = opmode.gamepad1.left_bumper //change this to change the button


        if (!(forwardWristButtonCurrentlyPressed && backwardWristButtonCurrentlyPressed)) { //saftey mechanism
            if (forwardWristButtonCurrentlyPressed && !forwardWristButtonPreviouslyPressed) {
                changePosition("forward")
            }
            if (backwardWristButtonCurrentlyPressed && !backwardWristButtonPreviouslyPressed) {
                changePosition("backward")
            }
        }

        forwardWristButtonPreviouslyPressed = forwardWristButtonCurrentlyPressed
        backwardWristButtonPreviouslyPressed = backwardWristButtonCurrentlyPressed

    }
}