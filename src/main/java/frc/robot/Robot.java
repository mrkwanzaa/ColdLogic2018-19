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
import edu.wpi.first.wpilibj.Encoder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.hal.AccumulatorResult;

public class Robot extends TimedRobot {
  private MotorLayout robo= new MotorLayout(6);

//Motor Controllers
  private final Joystick lstick = new Joystick(0);
  private final Joystick rstick = new Joystick(1);
  private final JoystickButton rightTrig = new JoystickButton(rstick, 1);
  
  private final JoystickButton rightThub = new JoystickButton(rstick, 2);

//input areas
  private final Timer m_timer = new Timer();
  private final int ultraPort = 0;
  private final double conversion = 110.7027140386606;//voltage to cm
  public double currentDistance = 0;
  private double x;
	private double y;
	private double stickY;
  private double stickX;
  private double stickSense;
  private double sucko = 0;
  private boolean suckoDown = true;
  final int downValue = 0;
  final int upvalue = 1;
//Varius ints and timers
private Encoder slideEncode = new Encoder(0,1);
private AnalogInput distanceSensor = new AnalogInput(ultraPort);
//sensors




  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    CameraServer.getInstance().startAutomaticCapture();
    slideEncode.reset();
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
    //double currentDistance = ultrasonic.getValue() * conversion;
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
      stickSense = lstick.getRawAxis(3) + 2;
      
      //Right side
      robo.getController(0).set((stickY+stickX) / stickSense);
      robo.getController(1).set((stickY+stickX) / stickSense);
      //left side
      robo.getController(2).set((-stickY+stickX) / stickSense);
      robo.getController(3).set((-stickY+stickX) / stickSense);


      // slido MODE
      if(slideEncode.get() < upvalue && slideEncode.get() > downValue)
        robo.getController(5).set(rstick.getY());
      else if(slideEncode.get() >= upvalue && rstick.getY() < 0)
        robo.getController(5).set(rstick.getY());
      else if(slideEncode.get() <= downValue && rstick.getY() > 0)
        robo.getController(5).set(rstick.getY());
      if (rightTrig.get()) {
        suckoDown = false;
        System.out.println("Suck on");
      }
      else if(rightThub.get()){
        suckoDown = true;
        System.out.println("Suck off");
      }

      if(suckoDown && sucko > 0.0){
        sucko-= 0.01;
      }
      else if(!suckoDown && sucko < 1.00){ 
        sucko+=0.01;
      }
      robo.getController(4).set(sucko);



      //distance sensor reading
      System.out.println();
      //System.out.println(distanceSensor.getVoltage() * conversion);
      
  }
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}