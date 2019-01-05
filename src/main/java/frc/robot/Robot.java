/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.AnalogInput;

public class Robot extends TimedRobot {
  private final Spark spark0 = new Spark(0);
  private final Spark spark1 = new Spark(1);
  private final Spark spark2 = new Spark(2);
  private final Spark spark3 = new Spark(3);
  private final Spark spark4 = new Spark(4);
//Motor Controllers
  private final Joystick lstick = new Joystick(0);
  private final Joystick rstick = new Joystick(1);
  private final JoystickButton right3 = new JoystickButton(rstick, 3);
//input areas
  private final Timer m_timer = new Timer();
  private final int ultraPort = 0;
  private final double conversion = 0.5;//voltage to cm
  public double currentDistance = 0;
//Varius ints and timers
private AnalogInput ultrasonic = new AnalogInput(ultraPort);
//sensors
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
  }

  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    //pass test comment
    double currentDistance = ultrasonic.getValue() * conversion;
    // Drive for 2 seconds
    if (m_timer.get() < 2.0) {
       // drive forwards half speed
    } else {
       // stop robot
    }
  }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  
  double multi = 0.9;
  @Override
  public void teleopPeriodic() {
      spark0.set(-lstick.getY()*multi-lstick.getX()*0.4);
    	spark1.set(lstick.getY()*multi-lstick.getX()*0.4);
    	spark2.set(lstick.getY()*multi-lstick.getX()*0.4);
    	spark3.set(-lstick.getY()*multi-lstick.getX()*0.4);
    	
    	
    	
    	spark0.set(-lstick.getY()*multi-lstick.getX()*0.2);
    	spark1.set(lstick.getY()*multi-lstick.getX()*0.2);
    	spark2.set(lstick.getY()*multi-lstick.getX()*0.2);
    	spark3.set(-lstick.getY()*multi-lstick.getX()*0.2);
    	
    if(right3.get())
    {
	    spark4.set(0.5);
    }
    else
    {
      spark4.set(0);
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
