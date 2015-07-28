package org.usfirst.frc.team2601.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team2601.robot.commands.Drive;
import org.usfirst.frc.team2601.robot.util.HawkCANTalon;
import org.usfirst.frc.team2601.robot.util.HawkLoggable;
import org.usfirst.frc.team2601.robot.util.HawkLogger;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	HawkCANTalon frontLeftCANTalon = new HawkCANTalon(1, "frontLeftCANTalon");
	HawkCANTalon middleLeftCANTalon = new HawkCANTalon(12, "middleLeftCANTalon");
	HawkCANTalon rearLeftCANTalon = new HawkCANTalon(14, "rearLeftCANTalon");
	HawkCANTalon frontRightCANTalon = new HawkCANTalon(4, "frontRightCANTalon");
	HawkCANTalon middleRightCANTalon = new HawkCANTalon(2, "middleRightCANTalon");
	HawkCANTalon rearRightCANTalon = new HawkCANTalon(3, "rearRightCANTalon");
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive());
    }
    private ArrayList<HawkLoggable> loggingList = new ArrayList<HawkLoggable>();
	public HawkLogger logger;
	
    public Drivetrain(){
    	loggingList.add(frontLeftCANTalon);
		loggingList.add(middleLeftCANTalon);
		loggingList.add(rearLeftCANTalon);
		loggingList.add(frontRightCANTalon);
		loggingList.add(middleRightCANTalon);
		loggingList.add(rearRightCANTalon);
		logger = new HawkLogger("drivetrain", loggingList);
		logger.setup();
    }
    
    private void matchMotors(CANTalon leader, CANTalon follower){
    	follower.set(leader.get());
    }
    
    RobotDrive drive = new RobotDrive(frontLeftCANTalon, middleLeftCANTalon, frontRightCANTalon, middleRightCANTalon);
    public void driveRobot(Joystick stick){
    	drive.arcadeDrive(stick.getY(), stick.getTwist());
    	matchMotors(frontLeftCANTalon, rearLeftCANTalon);
    	matchMotors(frontRightCANTalon, rearRightCANTalon);
    	logger.log(true);
    }
}

