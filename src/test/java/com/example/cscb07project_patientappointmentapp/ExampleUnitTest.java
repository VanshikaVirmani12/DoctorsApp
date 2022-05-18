package com.example.cscb07project_patientappointmentapp;

import android.app.Activity;

import com.example.cscb07project_patientappointmentapp.quickstart.Contract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {


    private Presenter presenter;
    private Activity activity = new Activity();
    private String username = new String();
    private String password = new String();

    @Mock
    private Contract.View view;

    @Mock
    Model model;

    @Captor
    private ArgumentCaptor<Contract.onLoginListener> loginListenerArgumentCaptor;

    @Before
    public void testPresenter() {
        //when(view.LoginCheckIfCurrUserNull()).thenReturn(0);
        MockitoAnnotations.initMocks(this);
        presenter = new Presenter(view);

    }

    @Test
    public void login() {
        presenter.login(activity, username, password);
        //verify(model(loginListenerArgumentCaptor.capture()));

//
//    @Test
//    public void testPresenter(){
//        //when(view.LoginCheckIfCurrUserNull()).thenReturn(true);
//
//        assertEquals(model.performFirebaseLogin(view, "vansvirm@gmail.com", "123456"),void);
//
//        Presenter presenter = new Presenter(view, model);
//
//       // assertEquals(view., null);
//
//        //verify(view).LoginCheckIfCurrUserNull();
//
//        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
//       // verify(view).
//    }
    }

}