package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

  public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;

  private Joystick m_leftStick;
  private Joystick m_rightStick;
  private XboxController Xbox;

  private static final int leftDevice1ID = 1;
  private static final int leftDevice2ID = 2;  
  private static final int rightDevice3ID = 3;
  private static final int rightDevice4ID = 4;

  private CANSparkMax m_leftMotor;
  private CANSparkMax m_leftMotor2;
  private CANSparkMax m_rightMotor;
  private CANSparkMax m_rightMotor2;
  
  @Override
  public void robotInit() {

  m_leftMotor = new CANSparkMax(leftDevice1ID, MotorType.kBrushless);
  m_leftMotor2 = new CANSparkMax(leftDevice2ID, MotorType.kBrushless);
  m_rightMotor = new CANSparkMax(rightDevice3ID, MotorType.kBrushless);
  m_rightMotor2 = new CANSparkMax(rightDevice4ID, MotorType.kBrushless);


  m_leftMotor.restoreFactoryDefaults();
  m_leftMotor2.restoreFactoryDefaults();
  m_rightMotor.restoreFactoryDefaults();
  m_rightMotor2.restoreFactoryDefaults();

  var leftside = new SpeedControllerGroup(m_leftMotor, m_leftMotor2);
  var rightside = new SpeedControllerGroup(m_rightMotor, m_rightMotor2);

  rightside.setInverted(true);
  leftside.setInverted(true);

  m_myRobot = new DifferentialDrive(leftside, rightside);
     
  m_rightStick = new Joystick(1);
  m_leftStick = new Joystick(0);
  Xbox = new XboxController(2);

  }
  
private double startTime;

@Override
public void autonomousInit() {
startTime = Timer.getFPGATimestamp();
}

@Override
public void autonomousPeriodic(){ 
double time = Timer.getFPGATimestamp();
}

@Override
  public void teleopPeriodic() {
    m_myRobot.tankDrive(m_leftStick.getY(), m_rightStick.getY());
}}