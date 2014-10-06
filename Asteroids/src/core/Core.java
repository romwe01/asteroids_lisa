package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import messenger.Messenger;
import collider.CollisionTester;
import entities.Entity;
import entities.EntityManager;
import events.EventListener.EventType;
import events.EventManager;
import graphics.GraphicsConfig;
import graphics.GraphicsSystem;
import input.InputManager;
import score.Score;
import score.ScoreComparator;
import statemachine.CreditsState;
import statemachine.EndState;
import statemachine.HighscoreState;
import statemachine.MenuState;
import statemachine.PlayState;
import statemachine.SplashState;
import statemachine.State;
import statemachine.StateMachine;
import update.UpdateManager;

public class Core{

	private float dt;
	private boolean runLoop = true;
	
	public UpdateManager updateManager;
	public InputManager inputManager;
	public EntityManager entityManager;
	public EventManager eventManager;
	public GraphicsSystem graphicsSystem;
	public GraphicsConfig graphicsConfig;
	public CollisionTester collisionT;
	public Messenger messenger;
	
	public State splashState;
	public State menuState;
	public State playState;
	public State endState;
	public State creditsState;
	public State highscoreState;
	public StateMachine stateMachine;
	
	public int score =0;
	public ArrayList<Score> scores;
	public File file;
	
	
	/**
	 * is the core of the game, implements all needed pieces for running the
	 * game
	 * 
	 * @param graphicsConfig
	 * @throws IOException 
	 */
	public Core(GraphicsConfig graphicsConfig){
		updateManager = new UpdateManager();
		inputManager = new InputManager(this);
		
		eventManager = new EventManager();
		graphicsSystem = new GraphicsSystem(this);
		
		this.graphicsConfig = graphicsConfig;
		
		setGraphicsConfiguration(this.graphicsConfig);
		graphicsSystem.addInputManager(inputManager);
		
		scores = new ArrayList<Score>();
		//Read xML File for Highscore
		file = new File("scores.txt");
		try {
			readFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.setupStateMachine();
	}

	private void readFile() throws IOException {
		
		if (!file.exists()){
			file.createNewFile();
		}
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = reader.readLine();
		while (line != null){
			String[]parts = line.split(" ");
			scores.add(new Score(parts[1], Integer.parseInt(parts[0])));
			line = reader.readLine();
		}
		reader.close();	
		Collections.sort(scores, new ScoreComparator());
	}

	public float getDT() {
		return dt;
	}
	
	public void setDT(float dt){
		this.dt = dt;
	}
	
	public void closeWindow() {
		writeFile();
		graphicsSystem.close();
	}
	
	private void writeFile() {
		try {
			if (!file.exists()){
				file.createNewFile();
			}
				
			FileOutputStream fos = new FileOutputStream(file);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			Iterator<Score> itr = scores.iterator();
			while (itr.hasNext()){
				String element = itr.next().toString();
				bw.write(element);
				
				bw.newLine();
			}
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void requestUpdate(){
		eventManager.handleEvents();
		//update and render each state
		stateMachine.update();
		messenger.update();
	}
	
	public void addEntity(Entity e){
		entityManager.addEntity(e);
	}

	
	/**
	 * opens the window and sets the frame rate
	 * 
	 * @param gc
	 */
	private void setGraphicsConfiguration(GraphicsConfig g){
		graphicsSystem.setFrameRate(g.FPS);
		graphicsSystem.open(g.Width, g.Height, g.Fullscreen);
	}

	public boolean isRunLoop() {
		return runLoop;
	}

	public void setRunLoop(boolean runLoop) {
		this.runLoop = runLoop;
	}

	/**
	 * setup state machine, which manages all game states
	 */
	private void setupStateMachine(){
		messenger = new Messenger();
		messenger.subscribe(() -> runLoop = false, "quit");
		messenger.subscribe(() -> stateMachine.handle("gameover"), "gameover");
		
		//setup state machine
		playState 		= new PlayState(messenger, eventManager, this);
		splashState 	= new SplashState(messenger, this);
		endState 		= new EndState(messenger);
		stateMachine 	= new StateMachine(splashState, playState);
		menuState 		= new MenuState(messenger, this, stateMachine);
		creditsState 	= new CreditsState(messenger, this);
		highscoreState 	= new HighscoreState(messenger, this);
		
		//add states
		stateMachine.addState(menuState);
		stateMachine.addState(endState);
		stateMachine.addState(creditsState);
		stateMachine.addState(highscoreState);
		
		//add transitions between game states
		stateMachine.addTransition(splashState, "m", menuState);
		stateMachine.addTransition(menuState, "play", playState);
		stateMachine.addTransition(menuState, "exit", endState);

		stateMachine.addTransition(menuState, "highscore", highscoreState);
		stateMachine.addTransition(menuState, "credits", creditsState);
		stateMachine.addTransition(playState, "m", menuState);
		stateMachine.addTransition(playState, "q", endState);
		stateMachine.addTransition(playState, "gameover", menuState);
		stateMachine.addTransition(highscoreState, "m", menuState);
		stateMachine.addTransition(creditsState, "m", menuState);
		
		eventManager.addListener(stateMachine, getType());
	}

	public String getType() {
		return EventType.KEY.name();
	}
}
