package entities;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import statemachine.PlayState;
import statemachine.SplashState;
import statemachine.State;
import statemachine.StateMachine;
import messenger.Messenger;

public class Test {
   String firstname;
   String lastname;
   public Test() {}
   public Test(String firstname, String lastname) {
      this.firstname = firstname;
      this.lastname = lastname;}
   public void hello() {
	   
      System.out.println("Hello " + firstname + " " + lastname);}
   public static void main(String[] args) {

	   Runnable r2 = () -> System.out.println("Hello world two! miau");
	   Runnable r4 = () -> System.out.println("Hello world 4! miau");
	   Runnable r5 = () -> System.out.println("Hello world 5! bark");
	   Runnable r6 = () -> System.out.println("Hello world 6! bark");
	   
	   Messenger messenger = new Messenger();
	   
	   messenger.subscribe(r2, "cats");
	   messenger.subscribe(r4, "cats");
	//   messenger.subscribe(r5, "dogs");
	//  messenger.subscribe(r6, "dogs");
	   messenger.unsubscribe(r6, "dogs");
	   messenger.send("dogsasdasd");
	   messenger.send("cats");
	   
	   messenger.update();
	   
		// State machine
//		State play = new PlayState("play", messenger);
//		State splash = new SplashState("splash", messenger);
//		StateMachine sm = new StateMachine("state machine", splash, play);
		
		
	//	// add game states
	//	sm.addState(play);
	//	sm.addState(splash);
		
		// add transitions between game states
//		sm.addTransition(splash, "x", play);
//		
//		// Messenger
//		Messenger m = new Messenger();
//		m.subscribe(() -> sm.handle("x"), "x");
//		
//		sm.handle("x");
	
   }
   
   
   
}