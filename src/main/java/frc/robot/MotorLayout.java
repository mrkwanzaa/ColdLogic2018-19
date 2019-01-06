package frc.robot;

import edu.wpi.first.wpilibj.PWMSpeedController;
import java.util.ArrayList;
import edu.wpi.first.wpilibj.Spark;

public class MotorLayout 
{
    private ArrayList<PWMSpeedController> sparkList;

    public MotorLayout(int numControllers)
    {
        sparkList = new ArrayList<PWMSpeedController>();
        for(int i = 0; i < numControllers; i++)
        {
            sparkList.add(new Spark(i));
        }
    }

    public PWMSpeedController getController(int num)
    {
        return sparkList.get(num);
    }
}