/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.AnalogInput;

public class Robot extends TimedRobot {
  private MotorLayout robo= new MotorLayout(5);
//Motor Controllers
  private final Joystick lstick = new Joystick(0);
  private final Joystick rstick = new Joystick(1);
  private final JoystickButton right3 = new JoystickButton(rstick, 3);
//input areas
  private final Timer m_timer = new Timer();
  private final int ultraPort = 0;
  private final double conversion = 0.5;//voltage to cm
  public double currentDistance = 0;
  private double x;
	private double y;
	private double stickY;
	private double stickX;
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
  //Github test comment
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
    double currentDistance = ultrasonic.getValue() * conversion;
    // Drive for 2 seconds
    if (m_timer.get() < 2.0) {
       // drive forwards half speed
       robo.getController(0).set(-0.5);
       robo.getController(1).set(0.5);
       robo.getController(2).set(0.5);
       robo.getController(3).set(-0.5);
    } else {
       // stop robot
       robo.getController(0).set(0);
       robo.getController(1).set(0);
       robo.getController(2).set(0);
       robo.getController(3).set(0);
    }
  }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
    x = 0;
    y = 0;
  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  
  @Override
  public void teleopPeriodic() {
      stickY = lstick.getY();
      stickX = lstick.getX();

      
      //Right side
      robo.getController(0).set(stickY+stickX);
      robo.getController(1).set(stickY+stickX);
      //left side
      robo.getController(2).set(-stickY+stickX);
      robo.getController(3).set(-stickY+stickX);
      //y stepper
    	/*if((y > 0 && stickY < y) || (y < 0 && stickY > y))
    	{
    		y = stickY;
    	}
    	else if(stickY < -0.1  && y > stickY)
    	{
    		y -= 0.2;
    	}
    	else if(stickY > 0.1  && y < stickY)
    	{
    		y += 0.2;
    	}
    	//x Stepper
    	if((x > 0 && stickX < x) || (x < 0 && stickX > x))
    	{
    		x = stickX;
    	}
    	else if(stickX < -0.1  && x > stickX)
    	{
    		x -= 0.2;
    	}
    	else if(stickX > 0.1  && x < stickX)
    	{
    		x += 0.2;
      }

      robo.getController(0).set(-y+x);
      robo.getController(1).set(y-x);
      robo.getController(2).set(y+x);
      robo.getController(3).set(-y-x);*/
      //this comment made by comment gang
    if(right3.get())
    {
	    robo.getController(4).set(0.5);
    }
    else
    {
      robo.getController(4).set(0);
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
