package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

  public class Robot extends TimedRobot {

  private DifferentialDrive m_myRobot;

  private Joystick m_leftStick;
  private Joystick m_rightStick;
  private XboxController Xbox;

  private static final int leftDevice1ID = 1;
  private static final int leftDevice2ID = 2;  
  private static final int rightDevice3ID = 3;
  private static final int rightDevice4ID = 4;
  private static final int m_leftboom5ID = 5;
  private static final int m_rightboom6ID = 6;
  private static final int m_intakeDevice7ID = 7;
  private static final int m_shooter8ID = 8;
  private static final int m_shooter2ID = 9;

  private CANSparkMax m_leftMotor;
  private CANSparkMax m_leftMotor2;
  private CANSparkMax m_rightMotor;
  private CANSparkMax m_rightMotor2;
  private CANSparkMax m_leftboom;
  private CANSparkMax m_rightboom;
  private CANSparkMax m_shooter;
  private CANSparkMax m_shooter2;
  private CANSparkMax m_intake;

  @Override
  public void robotInit() {

  m_leftMotor = new CANSparkMax(leftDevice1ID, MotorType.kBrushless);
  m_leftMotor2 = new CANSparkMax(leftDevice2ID, MotorType.kBrushless);
  m_rightMotor = new CANSparkMax(rightDevice3ID, MotorType.kBrushless);
  m_rightMotor2 = new CANSparkMax(rightDevice4ID, MotorType.kBrushless);
  m_leftboom = new CANSparkMax(m_leftboom5ID, MotorType.kBrushless);
  m_rightboom = new CANSparkMax(m_rightboom6ID, MotorType.kBrushless);
  m_intake = new CANSparkMax(m_intakeDevice7ID, MotorType.kBrushed);
  m_shooter = new CANSparkMax(m_shooter8ID, MotorType.kBrushless);
  m_shooter2 = new CANSparkMax(m_shooter2ID, MotorType.kBrushless);


  m_leftMotor.restoreFactoryDefaults();
  m_leftMotor2.restoreFactoryDefaults();
  m_rightMotor.restoreFactoryDefaults();
  m_rightMotor2.restoreFactoryDefaults();
  m_leftboom.restoreFactoryDefaults();
  m_rightboom.restoreFactoryDefaults();
  m_shooter.restoreFactoryDefaults();
  m_shooter2.restoreFactoryDefaults();

  m_leftMotor2.follow(m_leftMotor);
  m_rightMotor2.follow(m_rightMotor);
  m_leftboom.follow(m_rightboom);

  m_myRobot = new DifferentialDrive(m_leftMotor, m_rightMotor);
     
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

//autonomous 2 note front
if(time - startTime > 0.5) {
  m_rightboom.set (0.10);}

if(time - startTime > 2) {
  m_shooter.set (0.5); 
m_rightboom.set (0);
}

if(time - startTime > 4){
  m_intake.set(0.25);

} if(time - startTime > 6) {
  m_shooter.set(0);
  m_myRobot.tankDrive(0.4, 0.4);

}if(time - startTime > 8) {
  m_myRobot.tankDrive(0, 0);

if(time - startTime > 8.5) {
  m_intake.set(0);}

if(time - startTime > 9) {
  m_myRobot.tankDrive(-0.4, -0.4); }

if(time - startTime > 11) {
  m_myRobot.tankDrive(0, 0);
  m_shooter.set(0.5);
}
if(time - startTime > 11.5) {
  m_intake.set(0.25);}

if(time - startTime > 13.5) {
  m_shooter.set(0);
  m_intake.set(0); }

//autonomous #2

// if(time - startTime > 0.25) {
//   m_rightboom.set(0.10);
//   m_shooter.set(0.5); }

// if(time - startTime > 1) {
//   m_intake.set(0.25);
//   m_rightboom.set(0);}

// if(time - startTime > 2) {
//   m_myRobot.tankDrive(-0.4, 0.4);
//   m_shooter.set(0); }

// if(time - startTime > 2.5) {
//   m_myRobot.tankDrive(0, 0);}

// if(time - startTime > 2.75) {
//   m_myRobot.tankDrive(0.4, 0.4 );}

// if(time - startTime > 4.75) {
//   m_myRobot.tankDrive(0, 0);}

// if(time - startTime > 5.75) {
//   m_myRobot.tankDrive(-0.4, -0.4);
//   m_intake.set(0);}

// if(time - startTime > 7.75); {
//   m_myRobot.tankDrive(0, 0);
//   m_shooter.set(0.5);
// }
}
}


@Override
  public void teleopPeriodic() {
  m_myRobot.tankDrive(-m_leftStick.getY(), m_rightStick.getY());

  double boom = Xbox.getRawAxis(1) * 0.6;{
  }

  m_leftboom.set(boom);
  m_rightboom.set(boom);  

  if(Xbox.getRawButton(1)) {
  m_intake.set(0.15);}
 else if(Xbox.getRawButton(2)) {
  m_intake.set(-0.15);
} else {
  m_intake.set(0.0);
}

  if(Xbox.getRawButton(8)) {
  m_shooter.set(0.5);
  m_shooter2.set(0.5);
}else if (Xbox.getRawButton(5)) {
  m_shooter.set(0.25);
  m_shooter2.set(0.25);}
  else{
  m_shooter.set(0);
  m_shooter2.set(0);
}}}