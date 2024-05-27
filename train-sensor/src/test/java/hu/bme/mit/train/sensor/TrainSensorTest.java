package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

import static org.mockito.Mockito.*;

import javax.naming.ldap.Control;

public class TrainSensorTest {

    TrainController controller;
	TrainSensor sensor;
	TrainUser user;

    @Before
    public void before() {
        controller = mock(TrainController.class);
        user = mock(TrainUser.class);
        sensor = new TrainSensorImpl(controller, user);//mock(TrainSensor.class);

    }

    @Test
    public void LessThanZero() {
        sensor.overrideSpeedLimit(-1);
        verify(user, times(1)).setAlarmState(true);
        
    }

    @Test
    public void MoreThan500() {
        sensor.overrideSpeedLimit(501);
        verify(user, times(1)).setAlarmState(true);
        
    }

    @Test
    public void CorrectValue() {
        sensor.overrideSpeedLimit(300);
        verify(user, times(1)).setAlarmState(false);
        
    }


    @Test
    public void IncorrectValue() {
        sensor.overrideSpeedLimit(300);
        sensor.overrideSpeedLimit(100);
        verify(user, times(1)).setAlarmState(false);
        verify(user, times(1)).setAlarmState(true);
        
    }
}
/*  */