package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode

object Config {
    lateinit var opmode: OpMode //opmode var innit
    fun initControls(opmode: OpMode){ //init gamepad data
        this.opmode = opmode
    }
    fun updateControls() {

    }
}