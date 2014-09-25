import messenger
import pygame
from pygame.locals import*

class State:

    def __init__(self, pygame, display):
        self.activated = False
        self.pygame = pygame;
        self.display = display;

    def activate(self):
        self.activated  = True;

    def deactivate(self):
        self.activated = False;

    def handle(self, msgType, param = None):
        print("State handles: "+msgType)

    def update(self, display):
        pass

  #  def __repr__(self):
  #      return "Generic State"

class TestState(State):

    def __init__(self, name):
     #   super(TestState, self).__init__()
        self.name = name

    def activate(self):
    #    super(TestState, self).activate()
        print(self.name + " has been activated.")

    def deactivate(self):
    #    super(TestState, self).deactivate()
        print(self.name + " has been deactivated.")

    def handle(self, msgType, param = None):
     #   super(TestState, self).handle(msgType, param)
        pass

    def update(self, display):
        print("update some shit.")

 #   def __repr__(self):
 #       return "State with name="+self.name

class SplashState(State):

    def __init__(self, name, pygame, display):
      #  super(TestState, self).__init__()
        self.name = name
        self.pygame = pygame
        # Fill background
        self.background = pygame.Surface(display.get_size())
        self.background = self.background.convert()
        self.background.fill((250, 250, 250))
        # Display some text
        self.font = pygame.font.Font(None, 36)
        self.splashtext = self.font.render("Splash Screen", 1, (10, 10, 10))
        self.textpos = self.splashtext.get_rect()
        self.textpos.centerx = self.background.get_rect().centerx
        self.background.blit(self.splashtext, self.textpos)

    def activate(self):
    #    super(TestState, self).activate()
        print(self.name + " has been activated.")

    def deactivate(self):
    #    super(TestState, self).deactivate()

        print(self.name + " has been deactivated.")

    def handle(self, msgType, param = None):
     #   super(TestState, self).handle(msgType, param)
        pass

    def update(self, display):
        for event in self.pygame.event.get():
            
            if event.type == QUIT:
                self.pygame.quit()
                sys.exit()
            elif event.type == KEYDOWN and (event.key == K_x):
                print("x pressend")
                messenger.send('x');

        display.blit(self.splashtext, (0, 0))
        print("update splash state.")

class PlayState(State):

    def __init__(self, name, entitysystem):
    #    super(TestState, self).__init__()
        self.name = name
        self.entitysystem = entitysystem;

    def activate(self):
    #    super(TestState, self).activate()
        print(self.name + " has been activated.")

    def deactivate(self):
    #    super(TestState, self).deactivate()

        print(self.name + " has been deactivated.")

    def handle(self, msgType, param = None):
     #   super(TestState, self).handle(msgType, param)
        pass

    def update(self, display):
        self.entitysystem.update();
        self.entitysystem.draw();
        messenger.send("render");

        # Delay addition and removal of entites by one game loop cycle
        while len(self.entitysystem.functions) > 0:
            self.entitysystem.functions.pop(0)()
        print("update play state.")

class StateMachine(State):

    def __init__(self):
        # calls method from parent of StateMachine, which is State.
    #    super(StateMachine, self).__init__()
        self._cur_state = None
        self.states = {}




    def activate(self):
    #    super(StateMachine, self).activate()
        pass

    def deactivate(self):
     #   super(StateMachine, self).deactivate()
        pass

    def update(self, display):
    #    super(StateMachine, self).update()
        self._cur_state.update(display)

    def handle(self, msgType):
        print("handle with "+msgType)
    #    super(StateMachine, self).handle(msgType, param)
        print("current state is " + self._cur_state.name)

        if msgType in self.states[self._cur_state]:
            print("switch to next state ")
            self._cur_state = self.states[self._cur_state][msgType]
        self._cur_state.handle(msgType)

    def addState(self, state):
        self.states[state] = {}

    def addTransition(self, source, message, target):
        self.states[source][message] = target

    def setStartEnd(self, start, end):
        self.startState = start
        self.endState = end

        self.addState(start)
        self.addState(end)

        self._cur_state = self.startState

 #   def __repr__(self):
 #       return "StateMachine is in State "+str(self._cur_state)



