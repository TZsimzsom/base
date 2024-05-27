package hu.bme.mit.train.user;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class TrainUserImpl implements TrainUser {

	private TrainController controller;
	private int joystickPosition;
	private Timer timer;
	private Random random = new Random();

	TrainUserImpl() {
		//This constructor runs a TimerTask where it sets the joystick to a random position, adn then the controller follows the speed
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				int speed = random.nextInt();
				overrideJoystickPosition(speed);
				controller.followSpeed();
			}
		};
		timer.schedule(task, 0, 10);
	}

	public TrainUserImpl(TrainController controller) {
		this.controller = controller;
	}

	@Override
	public boolean getAlarmFlag() {
		return false;
	}

	@Override
	public int getJoystickPosition() {
		return joystickPosition;
	}

	@Override
	public void overrideJoystickPosition(int joystickPosition) {
		this.joystickPosition = joystickPosition;
		controller.setJoystickPosition(joystickPosition);
	}

}
